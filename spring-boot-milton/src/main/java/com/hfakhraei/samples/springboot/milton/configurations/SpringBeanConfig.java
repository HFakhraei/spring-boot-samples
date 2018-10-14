package com.hfakhraei.samples.springboot.milton.configurations;

import io.milton.servlet.MiltonFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

/**
 * This class configure Spring Beans.
 *
 * @author Hossein Fakhraei (HFakhraei@outlook.com)
 * @version 1 14 October 2018
 */
@Configuration
public class SpringBeanConfig {

    @Bean
    public FilterRegistrationBean someFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(getMiltonFilter());
        registration.setName("MiltonFilter");
        registration.addUrlPatterns("/*");
//        registration.addInitParameter("milton.exclude.paths", "/myExcludedPaths,/moreExcludedPaths");
        registration.addInitParameter("resource.factory.class",
                "io.milton.http.annotated.AnnotationResourceFactory");
        registration.addInitParameter("controllerPackagesToScan",
                "com.hfakhraei.samples.springboot.milton.controllers");
        registration.addInitParameter("milton.configurator",
                "com.hfakhraei.samples.springboot.milton.configurations.MiltonConfig");
        registration.setOrder(1);
        return registration;
    }

    public Filter getMiltonFilter() {
        return new MiltonFilter();
    }
}
