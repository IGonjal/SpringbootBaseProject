package com.igm.model.genetic;

public abstract class GrammarBase {
    private int[] chromosome;
    private int counter;
    private boolean hasReachedTerminal;
    private double variable;
    private int location;

    private final int maxBinary = 3;

    public GrammarBase(Chromosome chromosome, double variable) {
        this.chromosome = chromosome.getGenes();
        this.counter = 0;
        this.hasReachedTerminal = true;
        this.variable = variable;
        this.location = 0;
    }

    public double operate() {
        return mainDecisson();
    }

    private double mainDecisson() {

    }

    private double binary() {

        double op1 = mainDecisson();
        int operator = chromosome[location++];
        double op2 = mainDecisson();

        return BinaryOperators.operate(op1, op2, operator);
    }


    private double unary() {
        return UnaryOperators.operate(mainDecisson(),chromosome[location++]);
    }

    private static double terminal() {

    }
}
