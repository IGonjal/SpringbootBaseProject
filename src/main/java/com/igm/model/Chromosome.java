package com.igm.model;

import com.igm.model.interfaces.FitnessFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.Random;

public class Chromosome implements Comparable{
    private int genes[];

    @Autowired
    private FitnessFunction fitnessFunction;
    @Value("${chromosome.size}")
    private static int GENES_LENGTH;
    public static int GENES_LENGTH() {
        return GENES_LENGTH;
    }
    @Value("${chromosome.maxGeneValue}")
    private static int MAX_GENE_VALUE;
    public static int MAX_GENE_VALUE() {
        return MAX_GENE_VALUE;
    }


    public int[] getGenes() {
        return genes;
    }

    private double fitness;

    private Random randomGenerator;

    public Chromosome () {
        genes = new int[GENES_LENGTH];
        randomGenerator = new Random();
    }

    public Chromosome (int[] genes) {
        this.genes = genes;
        randomGenerator = new Random();
    }

    public Chromosome(Random randGenerator) {
        genes = new int[GENES_LENGTH];
        this.randomGenerator = randGenerator;
    }

    public Chromosome(Random randGenerator, int[] genes) {
        this.genes = genes;
        randomGenerator = randGenerator;
    }

    public void mutate () {
        genes[randomGenerator.nextInt(genes.length + 1)] = randomGenerator.nextInt(MAX_GENE_VALUE + 1);
    }

    public double calculateFitness() {
        this.fitness = fitnessFunction.getfitness(this);
        return this.fitness;
    }

    public void overcrossingTwoPoints(Chromosome other, int startPosition, int length) {
        int aux;
        for(int i = startPosition ; i < startPosition + length || i < genes.length ; i++) {
            aux = this.genes[i];
            this.genes[i] = other.genes[i];
            other.genes[i] = aux;
        }
    }


    private void initialize() {
        for(int i = 0; i < genes.length; i++){
            this.genes[i] = randomGenerator.nextInt(MAX_GENE_VALUE + 1);
        }
    }

    public static Chromosome buildRandomChromosome(){
        Chromosome chromosome = new Chromosome();
        chromosome.initialize();
        return chromosome;
    }

    @Override
    public int compareTo(Object o) {
        if (! (o instanceof Chromosome)) {
            return Integer.MIN_VALUE;
        }

        return Double.valueOf(this.fitness).compareTo(Double.valueOf(((Chromosome) o).fitness));
    }



}
