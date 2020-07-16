package com.igm.model;

import com.igm.model.interfaces.FitnessFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.Arrays;
import java.util.Random;

public class Chromosome implements Comparable{
    private int genes[];

    @Autowired
    private FitnessFunction fitnessFunction;
    @Value("${chromosome.size}")
    private int genesLength;
    @Value("${chromosome.maxGeneValue}")
    private static int maxGeneValue;
    private double fitness;
    private final Random randomGenerator;

    public int[] getGenes() {
        return Arrays.copyOf(genes,genes.length);
    }

    public Chromosome () {
        genes = new int[genesLength];
        randomGenerator = new Random();
    }

    public Chromosome (int[] genes) {
        this.genes = Arrays.copyOf(genes,genes.length);
        randomGenerator = new Random();
    }

    public void mutate () {
        genes[randomGenerator.nextInt(genes.length + 1)] = randomGenerator.nextInt(maxGeneValue + 1);
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
            this.genes[i] = randomGenerator.nextInt(maxGeneValue + 1);
        }
    }

    public static Chromosome buildRandomChromosome(){
        Chromosome chromosome = new Chromosome();
        chromosome.initialize();
        return chromosome;
    }

    @Override
    public int compareTo(Object theOtherObject) {
        if (! (theOtherObject instanceof Chromosome)) {
            return Integer.MIN_VALUE;
        }

        return Double.valueOf(this.fitness).compareTo(Double.valueOf(((Chromosome) theOtherObject).fitness));
    }



}
