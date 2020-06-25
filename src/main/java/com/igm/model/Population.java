package com.igm.model;

import com.igm.model.interfaces.CrossoverMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.Arrays;
import java.util.Map;
import java.util.Random;

public class Population {

    @Value("${population.trashWorstEnabled}:false")
    private boolean trashWorstEnabled;

    @Value("${population.mutationPercentage}")
    private static int MUTATION_PERCENTAGE;
    public static int MUTATION_PERCENTAGE() {
        return MUTATION_PERCENTAGE;
    }

    @Value("${population.eliteChromosomes}")
    private static int ELITE_CHROMOSOMES;
    public static int ELITE_CHROMOSOMES() {
        return ELITE_CHROMOSOMES;
    }

    @Autowired
    private Chromosome [] chromosomes;
    @Autowired
    private Random randomGenerator;
    @Autowired
    private CrossoverMethod crossoverMethod;

    private int generation;

    public Population(){
        this.generation = 0;
    }

    private void mutation() {
        for(int i = ELITE_CHROMOSOMES; i < chromosomes.length; i++) {
            if (randomGenerator.nextInt(100) < MUTATION_PERCENTAGE) {
                chromosomes[i--].mutate();
            }
        }
    }

    private void crossover(){
        Map<Integer, Integer> pairs = crossoverMethod.getPair(generation);
        for(int i = 0; i < ELITE_CHROMOSOMES ; i++){
            pairs.remove(i);
        }
        for(int i = ELITE_CHROMOSOMES ; i < chromosomes.length ; i++) {
            if(pairs.get(i) != null ) {
                chromosomes[i].overcrossingTwoPoints(chromosomes[pairs.get(Integer.valueOf(i))], randomGenerator.nextInt(Chromosome.GENES_LENGTH()), randomGenerator.nextInt(Chromosome.GENES_LENGTH()));
            }
        }
    }

    private void fitness(){
        for(Chromosome chromosome : chromosomes) {
            chromosome.calculateFitness();
        }
        Arrays.sort(chromosomes);
    }

    private void setTrashWorstEnabled(boolean enabled) {
        trashWorstEnabled = enabled;
    }

    private void trashWorst(int numOfTrashableChromosomes){
        if(trashWorstEnabled) {
            for (int i = chromosomes.length - (numOfTrashableChromosomes + 1); i < chromosomes.length; i++) {
                chromosomes[i] = Chromosome.buildRandomChromosome();
            }
        }
    }

    public void computeIteration(){
        mutation();
        crossover();
        fitness();
        sort();
        trashWorst();
    }

    public boolean perfectSolutionReached(){
        return chromosomes[0].calculateFitness() == 0d;
    }

    public void sort() {
        Arrays.sort(chromosomes);
    }

    public Chromosome getBest() {
        return chromosomes[0];
    }

    public void exchangeBest(Population other) {
        for(int i = 0; i < ELITE_CHROMOSOMES ; i++) {
            this.chromosomes[chromosomes.length-1-i] = other.chromosomes[i];
            other.chromosomes[chromosomes.length-1-i] = this.chromosomes[i];
        }
    }

    public void exchangeRandom(Population other) {
        this.chromosomes[randomGenerator.nextInt(chromosomes.length)] = other.chromosomes[randomGenerator.nextInt(chromosomes.length)];
        other.chromosomes[randomGenerator.nextInt(chromosomes.length)] = this.chromosomes[randomGenerator.nextInt(chromosomes.length)];
    }

}
