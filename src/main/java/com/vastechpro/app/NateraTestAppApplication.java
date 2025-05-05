package com.vastechpro.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.component.page.AppShellConfigurator;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@Theme("my-theme")
@Configuration
@EnableScheduling
@EnableCaching
public class NateraTestAppApplication implements AppShellConfigurator {

    public static void main(String[] args) {
        SpringApplication.run(NateraTestAppApplication.class, args);
    }
}
