package com.igm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = "classpath:application.properties")
public class SpringbootApp {
    private static Logger logger = LoggerFactory.getLogger(SpringbootApp.class);

    public static void main(String[] args) {
        logger.debug("initializing");
        SpringApplication.run(SpringbootApp.class, args);
    }
}
