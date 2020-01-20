package com.kevinkoleck.io.application.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Created by kevinkoleck on 1/19/20.
 */

@Configuration
@Getter
public class ApplicationConfig {
    @Value("${api.key}")
    private String apiKey;
}
