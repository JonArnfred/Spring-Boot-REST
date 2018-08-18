package org.name;

import org.name.interceptors.LogInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/** Created by j on 24/03/2018. */
@Configuration
@EnableAutoConfiguration
@EnableConfigurationProperties
@ComponentScan("org.name")
public class Application implements WebMvcConfigurer {

  @Autowired private LogInterceptor logInterceptor;

  public static void main(String[] args) {

    SpringApplication springApplication = new SpringApplication(Application.class);

    springApplication.run(args);
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    // use .addPathPatterns("/some/path/") to only add the interceptor for the specified path
    registry.addInterceptor(logInterceptor);
  }
}
