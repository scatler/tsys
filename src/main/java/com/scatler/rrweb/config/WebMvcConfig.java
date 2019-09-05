package com.scatler.rrweb.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by alexkpc on 27.07.2019.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.scatler.rrweb.controller"})
public class WebMvcConfig implements WebMvcConfigurer {
/*    @Bean
    public InternalResourceViewResolver resolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        //resolver.setViewClass(JstlView.class);
        resolver.setPrefix("/WEB-INF/admin/");
        resolver.setSuffix(".html");
        return resolver;
    }*/
/*

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp().prefix("/WEB-INF/admin/").suffix(".html");
    }
*/

/*
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
    }

*/

    @Bean
    public Logger logger(){
        return LoggerFactory.getLogger("RRD");
    }
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    /*    registry
                .addResourceHandler("/resources/**")// specifying resource folder
                .addResourceLocations("/resources/");
        registry.addResourceHandler("/scripts/**").addResourceLocations("/scripts");
        registry.addResourceHandler("/styles/**").addResourceLocations("/styles");*/
        //registry.addResourceHandler("/*.html/**").addResourceLocations("/WEB-INF/admin");
        //registry.addResourceHandler("index.html").addResourceLocations("/index.html");
        registry.addResourceHandler("/**").addResourceLocations("/");
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
