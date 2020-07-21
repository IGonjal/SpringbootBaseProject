package com.igm.model.genetic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public final class GramaticalEvolutionApp {

    private GramaticalEvolutionApp() {

    }

    public static void main(String[] args) {
        SpringApplication.run(GramaticalEvolutionApp.class, args);
    }
}
