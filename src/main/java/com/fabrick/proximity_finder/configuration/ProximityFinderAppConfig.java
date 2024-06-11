package com.fabrick.proximity_finder.configuration;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableCaching
public class ProximityFinderAppConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
