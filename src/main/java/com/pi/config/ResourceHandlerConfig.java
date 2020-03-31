package com.pi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//@Profile({"develop", "test"})
@Configuration
public class ResourceHandlerConfig extends WebMvcConfigurerAdapter {

    @Value("${project.root}")
    private String projectRoot;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("file:" + projectRoot + "/src/main/resources/static/");
    }
}

