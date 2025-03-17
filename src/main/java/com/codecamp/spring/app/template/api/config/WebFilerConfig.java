package com.codecamp.spring.app.template.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.WebFilter;

@Configuration
public class WebFilerConfig {

    //@Bean // activate this to add filter
    public WebFilter createRequestFilter() {
        return new RequestFilter();
    }
}
