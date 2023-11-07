package kr.co.fns.chat.core.config.resolver;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class Account {
    private final String integUid;
    private final LoginType loginType;
    private final String roles;
    private final String userIp;
    private final String tokenInfo;
    private final String countryCode;
    private final String country;
}