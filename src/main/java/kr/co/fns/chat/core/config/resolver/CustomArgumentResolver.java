package kr.co.fns.chat.core.config.resolver;

import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import kr.co.fns.chat.core.config.exception.AccountException;
import kr.co.fns.chat.core.config.exception.InvalidTokenException;
import kr.co.fns.chat.core.config.model.enums.ErrorCode;
import kr.co.fns.chat.core.config.model.request.TokenRequest;
import kr.co.fns.chat.core.config.properties.ExternalUrlProperties;
import kr.co.fns.chat.core.util.CommonUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.web.reactive.BindingContext;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.result.method.HandlerMethodArgumentResolver;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

import javax.servlet.http.HttpServletRequest;
import java.time.Duration;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Slf4j
public class CustomArgumentResolver implements HandlerMethodArgumentResolver {

    private final ReactiveJwtDecoder reactiveJwtDecoder;
    private final ExternalUrlProperties externalUrlProperties;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(CurrentUser.class);
    }

    @Override
    public Mono<Object> resolveArgument(MethodParameter parameter, BindingContext bindingContext,
                                        ServerWebExchange exchange) {

        log.debug("resolveArgument called....");

        var token = "";

        final String AUTHORIZATION = "access_token";
        final String USER_IP = "user_ip";

        for (String value : Objects.requireNonNull(
                exchange.getRequest().getHeaders().get(AUTHORIZATION))) {
                token = value.trim();
        }
        log.debug("token info => {}", token);

        log.debug(token);

        HttpServletRequest request = (HttpServletRequest) exchange.getRequest(); // .getNativeRequest(HttpServletRequest.class);
        String clientIp = CommonUtil.getClientIp(request); // getClientIp(request);
        String cfIpCountry = request.getHeader("CF-IPCountry");
        String country = cfIpCountry;

        // token validation check
        TokenRequest req = TokenRequest.builder()
                .requestUri(exchange.getRequest().getURI().getPath())
                .method(exchange.getRequest().getMethod())
                .token(token)
                .build();
        String accessToken = token;
        return checkAuthorization(req)
                .flatMap(result -> {
                    log.debug(result);
                    return reactiveJwtDecoder.decode(accessToken)
                            .flatMap(jwt -> {
                                if (StringUtils.isAnyEmpty(jwt.getClaimAsString("account_id"))
                                        || jwt.getClaim("ROLE") == null) {
                                    return Mono.error(new InvalidTokenException(ErrorCode.INVALID_TOKEN));
                                }
                                log.debug("Role => {}", jwt.getClaimAsString("ROLE"));
                                final var accountId = jwt.getClaimAsString("account_id");
                                final var roles = jwt.getClaimAsString("ROLE");  // (JSONArray) jwt.getClaim("ROLE");
                                final var email = jwt.getClaimAsString("user_id");

                                return Mono.just(Account.builder()
                                        .integUid(jwt.getClaimAsString("integUid"))
                                        .roles(jwt.getClaimAsString("roles"))
                                        .userIp(clientIp)
                                        .countryCode(cfIpCountry)
                                        .loginType(LoginType.valueOf(jwt.getClaimAsString("loginType")))
                                        .tokenInfo(accessToken)
                                        .build());
                            });
                });
    }


    private Mono<String> checkAuthorization(TokenRequest req) {
        return getWebClient(externalUrlProperties.getAuthUrl()).mutate().build()
                .method(HttpMethod.POST)
                .uri("/v1/access_token/info")
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(req))
                .retrieve()
                .onStatus(
                        httpStatus -> httpStatus != HttpStatus.OK,
                        clientResponse -> clientResponse.createException()
                                .flatMap(
                                        it -> {
                                            if (it.getStatusCode().equals(HttpStatus.CONFLICT)) {
                                                return Mono.error(new AccountException(ErrorCode.USER_ALREADY_LOGIN));
                                            } else if(it.getStatusCode().equals(HttpStatus.FORBIDDEN)) {
                                                return Mono.error(new AccountException(ErrorCode.AUTHORIZATION_FAIL));
                                            } else {
                                                return Mono.error(new AccountException(ErrorCode.EXPIRED_TOKEN));
                                            }
                                        }))
                .bodyToMono(String.class)
                .doOnError(error -> {
                    log.error("error {}", error.getMessage());
                    if(error.getMessage().equals(ErrorCode.USER_ALREADY_LOGIN.toString())) {
                        throw new AccountException(ErrorCode.USER_ALREADY_LOGIN);
                    } else if(error.getMessage().equals(ErrorCode.AUTHORIZATION_FAIL.toString())) {
                        throw new AccountException(ErrorCode.AUTHORIZATION_FAIL);
                    } else {
                        throw new AccountException(ErrorCode.EXPIRED_TOKEN);
                    }
                });
    }

    public WebClient getWebClient(String url) {
        ConnectionProvider provider = ConnectionProvider.builder("fixed")
                .maxIdleTime(Duration.ofSeconds(20))
                .maxLifeTime(Duration.ofSeconds(600))
                .pendingAcquireTimeout(Duration.ofSeconds(60))
                .evictInBackground(Duration.ofSeconds(120)).build();

        HttpClient httpClient = HttpClient.create(provider)
                .doOnConnected(
                        conn -> conn.addHandlerLast(new ReadTimeoutHandler(60, TimeUnit.SECONDS))
                                .addHandlerLast(new WriteTimeoutHandler(120, TimeUnit.SECONDS))
                ).compress(true).wiretap(true);

        return WebClient.builder()
                .baseUrl(url)
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }
}
