package com.igm.model;

import com.igm.model.interfaces.CrossoverMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.Arrays;
import java.util.Map;
import java.util.Random;

public class Population {

    @Value("${population.trashWorstEnabled:false}")
    private boolean trashWorstEnabled;

    @Value("${population.mutationPercentage}")
    private int mutationPercentage;

    @Value("${population.eliteChromosomes:4}")
    private int eliteChromosomes;

    @Value("${population.worseTrashed:0}")
    private int worseTrashed;

    @Value("${chromosome.size}")
    private int genesLength;



    @Autowired
    private Chromosome[] chromosomes;
    @Autowired
    private Random randomGenerator;
    @Autowired
    private CrossoverMethod crossoverMethod;

    private int generation;

    public Population() {
        this.generation = 0;
    }

    private void mutation() {
        for (int i = eliteChromosomes; i < chromosomes.length; i++) {
            if (randomGenerator.nextInt(100) < mutationPercentage) {
                chromosomes[i--].mutate();
            }
        }
    }

    private void crossover() {
        Map<Integer, Integer> pairs = crossoverMethod.getPair(generation);
        for (int i = 0; i < eliteChromosomes; i++) {
            pairs.remove(i);
        }
        for (int i = eliteChromosomes; i < chromosomes.length; i++) {
            if (pairs.get(i) != null) {
                chromosomes[i].overcrossingTwoPoints(chromosomes[pairs.get(Integer.valueOf(i))], randomGenerator.nextInt(genesLength), randomGenerator.nextInt(genesLength));
            }
        }
    }

    private void fitness() {
        for (Chromosome chromosome : chromosomes) {
            chromosome.calculateFitness();
        }
        Arrays.sort(chromosomes);
    }

    public void setTrashWorstEnabled(boolean enabled) {
        trashWorstEnabled = enabled;
    }

    private void trashWorst() {
        if (trashWorstEnabled) {
            for (int i = chromosomes.length - (worseTrashed + 1); i < chromosomes.length; i++) {
                chromosomes[i] = Chromosome.buildRandomChromosome();
            }
        }
    }

    public void computeIteration() {
        mutation();
        crossover();
        fitness();
        sort();
        if (trashWorstEnabled) {
            trashWorst();
        }
        this.generation++;
    }

    public boolean perfectSolutionReached() {
        return chromosomes[0].calculateFitness() == 0d;
    }

    public void sort() {
        Arrays.sort(chromosomes);
    }

    public Chromosome getBest() {
        return chromosomes[0];
    }

    public void exchangeBest(Population other) {
        for (int i = 0; i < eliteChromosomes; i++) {
            this.chromosomes[chromosomes.length - 1 - i] = other.chromosomes[i];
            other.chromosomes[chromosomes.length - 1 - i] = this.chromosomes[i];
        }
    }

    public void exchangeRandom(Population other) {
        this.chromosomes[randomGenerator.nextInt(chromosomes.length)] = other.chromosomes[randomGenerator.nextInt(chromosomes.length)];
        other.chromosomes[randomGenerator.nextInt(chromosomes.length)] = this.chromosomes[randomGenerator.nextInt(chromosomes.length)];
    }

}
