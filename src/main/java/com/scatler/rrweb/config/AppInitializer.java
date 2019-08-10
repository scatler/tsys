package com.scatler.rrweb.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

/**
 * Created by alexkpc on 27.07.2019.
 */
public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {
                AppContext.class
        };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {
                WebMvcConfig.class
        };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }


    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter filter = new CharacterEncodingFilter("UTF-8", true);
        return new Filter[] {filter};
    }


}
