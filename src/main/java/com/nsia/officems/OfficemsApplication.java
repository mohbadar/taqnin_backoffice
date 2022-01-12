package com.nsia.officems;

import java.util.Date;
import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.modelmapper.ModelMapper;
import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author hpardess
 *
 */
@ComponentScan(basePackages = { "com.*", "af.*" })
@EntityScan(basePackages = { "com.*", "af.*" })
@EnableJpaRepositories(repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean.class)
@SpringBootApplication
public class OfficemsApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(OfficemsApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(OfficemsApplication.class, args);
    }

    @PostConstruct
    public void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC")); // It will set UTC timezone
        System.out.println("Spring boot application running in UTC timezone :" + new Date()); // It will print UTC
                                                                                              // timezone
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
