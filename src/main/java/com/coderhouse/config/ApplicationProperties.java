package com.coderhouse.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties
public class ApplicationProperties {

    @Value(value = "${spring.redis.host}")
    private String host;
    @Value(value = "${spring.redis.port}")
    private Integer port;
    @Value(value = "${spring.redis.timeOfLife}")
    private Integer timeOfLife;

    @Value(value = "${jwt.secret}")
    private String jwtSecret;
    @Value(value = "${jwt.expiration}")
    private int expiration;

    @Value(value = "${spring.mail.username}")
    private String configurableEmail;

}
