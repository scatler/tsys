package com.scatler.rrweb.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by alexkpc on 27.07.2019.
 */
@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy
@ComponentScan(basePackages = {"com.scatler.rrweb.controller"})
public class WebMvcConfig implements WebMvcConfigurer {
    @Bean
    public Logger logger() {
        return LoggerFactory.getLogger("RRD");
    }

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("/");
        registry.addResourceHandler("/dev-release/**").addResourceLocations("/dev-release/");
        registry.addResourceHandler("/app/**").addResourceLocations("/dev-release/app/");
        registry.addResourceHandler("/lib/**").addResourceLocations("/dev-release/lib/");
        registry.addResourceHandler("/fonts/**").addResourceLocations("/dev-release/fonts/");
        registry.addResourceHandler("/sass/**").addResourceLocations("/dev-release/sass/");
        registry.addResourceHandler("/assets/**").addResourceLocations("/dev-release/assets/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
    }

    @Bean("messageSource")
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:locale/messages");
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setUseCodeAsDefaultMessage(true);
        return messageSource;
    }
}
