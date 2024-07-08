package com.example.dietAssistant.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "dietassistant.jwt")
@Data
public class JwtProperties {

    private Long ttl;

    private String secretkey;

    private String userid;

    private String usertoken;
}
