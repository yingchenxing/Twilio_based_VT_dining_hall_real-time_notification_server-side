package com.example.vthacks_serverside.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableScheduling
@ConditionalOnProperty(prefix="scheduled",name = "enable", havingValue = "true")
public class CorsConfig {
    private static final long MAX_AGE = 24 * 60 * 60;

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*"); // Set the access source address
        corsConfiguration.addAllowedHeader("*"); // Set the access source request header
        corsConfiguration.addAllowedMethod("*"); // Set the access source request method
        corsConfiguration.setMaxAge(MAX_AGE);
        source.registerCorsConfiguration("/**", corsConfiguration); // Configure cross-domain settings for the interface
        return new CorsFilter(source);
    }
}

