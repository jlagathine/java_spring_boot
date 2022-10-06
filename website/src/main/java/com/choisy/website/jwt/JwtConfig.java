package com.choisy.website.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

import com.google.common.net.HttpHeaders;


@ConfigurationProperties(prefix="application.jwt")
@EnableConfigurationProperties
@PropertySource("application.yml")
public class JwtConfig {

    private String secretKey;
    private String tokenPrefx;
    private Integer tokenExpirationAfterDays;
    
    public JwtConfig() {
    }

    public String getSecreKey() {
        return secretKey;
    }

    public void setSecreKey(String secreKey) {
        this.secretKey = secreKey;
    }

    public String getTokenPrefx() {
        return tokenPrefx;
    }

    public void setTokenPrefx(String tokenPrefx) {
        this.tokenPrefx = tokenPrefx;
    }

    public Integer getTokenExpirationAfterDays() {
        return tokenExpirationAfterDays;
    }

    public void setTokenExpirationAfterDays(Integer tokenExpirationAfterDays) {
        this.tokenExpirationAfterDays = tokenExpirationAfterDays;
    }
    
    public String getAutorization() {
        return HttpHeaders.AUTHORIZATION;
    }
    
}

