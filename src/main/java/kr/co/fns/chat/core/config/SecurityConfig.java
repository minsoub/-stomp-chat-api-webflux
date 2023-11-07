package kr.co.fns.chat.core.config;


import kr.co.fns.chat.core.config.exception.InvalidTokenException;
import kr.co.fns.chat.core.config.model.enums.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtReactiveAuthenticationManager;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.crypto.spec.SecretKeySpec;

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
@RequiredArgsConstructor
@Slf4j
public class SecurityConfig {

    @Value("${spring.jwt.secret}")
    private String jwtSecretKey;

    @Value("${chat.manager.public-routes}")
    private String[] publicRoutes;

    public ReactiveJwtDecoder reactiveJwtDecoder() {
        SecretKeySpec secretKey = new SecretKeySpec(jwtSecretKey.getBytes(), MacAlgorithm.HS256.getName());

        return NimbusReactiveJwtDecoder.withSecretKey(secretKey)
                .macAlgorithm(MacAlgorithm.HS256)  // .HS512)
                .build();
    }

    @Bean
    SecurityWebFilterChain springWebFilterChain(ServerHttpSecurity http) {
        return http
                .authorizeExchange(exchanges -> exchanges
                        //    .pathMatchers(publicRoutes).permitAll()
                        //.anyExchange().authenticated()
                        .anyExchange().permitAll()
                )
                .csrf().disable()
                .httpBasic().disable()
                .formLogin().disable()
                .oauth2ResourceServer().jwt(jwtSpec -> {
                    try {
                        log.debug("springWebFilterChaing called.... => {}", jwtSpec);
                        jwtSpec.authenticationManager(jwtReactiveAuthenticationManager(reactiveJwtDecoder()));
                    } catch (Exception e) {
                        log.debug("error => {}", e);
                        e.printStackTrace();
                        throw new InvalidTokenException(ErrorCode.INVALID_TOKEN);
                    }
                }).and().build();
    }
    public ReactiveAuthenticationManager jwtReactiveAuthenticationManager(ReactiveJwtDecoder reactiveJwtDecoder) {
        return new JwtReactiveAuthenticationManager(reactiveJwtDecoder);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

//@Configuration
//@EnableWebSecurity(debug = true)
//@EnableMethodSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//
//    @Bean
//    public PasswordEncoder getPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//    	http.httpBasic().disable()
//    	.cors().configurationSource(corsConfigurationSource())
//    	.and()
//    	.authorizeRequests()
//        .antMatchers("/**").permitAll()
//        .and()
//        .csrf().disable()
//        //.oauth2ResourceServer().jwt().decoder(jwtDecoder())
//        ;
//    }
//
//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//
//        configuration.addAllowedOrigin("*");
//        configuration.addAllowedHeader("*");
//        configuration.addAllowedMethod("*");
//        configuration.setMaxAge(3600L);
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }
//
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//
//    	// spring security > Cors 적용
//    	web.ignoring()
//                .antMatchers( "/swagger-ui/**", "/v3/api-docs/**");   // "/**",
//    }
//
//}
