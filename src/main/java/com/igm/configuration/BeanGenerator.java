package com.igm.configuration;

import com.igm.model.ExampleModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanGenerator {

    @Bean
    public ExampleModel exampleModelGenerator() {
        return new ExampleModel();
    }
}
