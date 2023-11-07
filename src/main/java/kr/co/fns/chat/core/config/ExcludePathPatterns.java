package kr.co.fns.chat.core.config;

import lombok.Builder;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Builder
public class ExcludePathPatterns {

    final public static Map<String, String> excludePathPatternsMap = Stream.of(
            new String[][] {
                    {"/swagger-ui.html", "NONE"},
                    {"/swagger-ui/**", "NONE"},
                    {"/v3/api-docs/**", "NONE"},
                    {"/api-docs/**", "NONE"},
                    {"/swagger-resources", "NONE"},
                    {"/swagger-resources/**", "NONE"},
                    {"/configuration/ui", "NONE"},
                    {"/configuration/security", "NONE"},
                    {"/webjars/**", "NONE"},
                    {"/actuator/**", "NONE"},
                    {"/actuator", "NONE"},
                    {"/favicon.ico", "NONE"},
                    {"/ws/**", "NONE"}
            }
    ).collect(Collectors.toMap(data -> data[0], data -> data[1]));
}
