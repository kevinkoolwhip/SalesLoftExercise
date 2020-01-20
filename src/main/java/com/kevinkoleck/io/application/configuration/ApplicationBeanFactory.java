package com.kevinkoleck.io.application.configuration;

import com.kevinkoleck.io.controller.ApplicationController;
import com.kevinkoleck.io.service.ApplicationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by kevinkoleck on 1/19/20.
 */

@Configuration
public class ApplicationBeanFactory {

    private final ApplicationConfig applicationConfig;

    public ApplicationBeanFactory(ApplicationConfig applicationConfig) {
        this.applicationConfig = applicationConfig;
    }

    @Bean
    public ApplicationService applicationService() throws Exception {
        if (applicationConfig.getApiKey().isEmpty()) {
            throw new Exception("API KEY IS NOT SET, SET ENVIRONMENT VARIABLE API_KEY WITH SALESLOFT API KEY");
        }
        return new ApplicationService(applicationConfig.getApiKey());
    }

    @Bean
    public ApplicationController applicationController(ApplicationService applicationService) {
        return new ApplicationController(applicationService);
    }
}
