package com.exam.configuration;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

public class WebConfiguration implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext servletAppConfiguration = new AnnotationConfigWebApplicationContext();
        servletAppConfiguration.register(ServletConfiguration.class);

        DispatcherServlet dispatcherServlet = new DispatcherServlet(servletAppConfiguration);
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", dispatcherServlet);

        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");

        AnnotationConfigWebApplicationContext rootAppConfiguration = new AnnotationConfigWebApplicationContext();
        rootAppConfiguration.register(RootConfiguration.class);

        ContextLoaderListener listener = new ContextLoaderListener(rootAppConfiguration);
        servletContext.addListener(listener);

//        FilterRegistration.Dynamic filter = servletContext.addFilter("encodingFilter", CharacterEncodingFilter.class);
//        filter.addMappingForServletNames(null, false, "dispathcer");
//        filter.setInitParameter("encoding", "UTF-8");
    }
}