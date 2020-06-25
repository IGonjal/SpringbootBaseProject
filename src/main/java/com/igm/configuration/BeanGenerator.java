package com.igm.configuration;

import com.igm.model.Chromosome;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class BeanGenerator {
    @Value("${population.populationSize}")
    private int populationSize;

    @Bean
    public Chromosome [] chromosomesGenerator() {
        Chromosome chromosomes[] = new Chromosome[populationSize];
        for(int i = 0; i < chromosomes.length; i++) {
            chromosomes[i] = Chromosome.buildRandomChromosome();
        }
        return chromosomes;
    }
}
