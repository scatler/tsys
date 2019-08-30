package com.scatler.rrweb.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.theme.ThemeChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

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
