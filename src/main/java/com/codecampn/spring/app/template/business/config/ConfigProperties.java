package com.codecampn.spring.app.template.business.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("my-section")
public class ConfigProperties {

    private String myProp;

    // you can load specific properties of application.yaml into this bean
    // via injection into other bean they are available wherever you want
    // in this way you can also use custom properties
    // it's an alternative to @Value for a single value
}
