package de.schroeder.checkout.config;

import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.h2.server.web.WebServlet;

/**
 * @author schroeder
 * @date 06. Jul 2016
 */
@Configuration
public class H2ServletConfig {

    @Bean
    public ServletRegistrationBean h2servletRegistration(){

        ServletRegistrationBean registrationBean = new ServletRegistrationBean( new WebServlet() );
        registrationBean.addUrlMappings("/console/*");
        return registrationBean;
    }
}