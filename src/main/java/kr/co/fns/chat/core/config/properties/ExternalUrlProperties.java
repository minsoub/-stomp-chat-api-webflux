package kr.co.fns.chat.core.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "spring.sso")
public class ExternalUrlProperties {
    private String authUrl;
    private String clientId;
    private String clientSecret;
}
