package com.igm.model.genetic;

public abstract class GrammarBase {
    private int[] chromosome;
    private int counter;
    private boolean hasReachedTerminal;
    private double variable;
    private int location;
    private static final BinaryOperator[] binaryOperators;
    private static final UnaryOperator[] unaryOperators;
    private static final Terminal[] terminals;

    static {
        binaryOperators = new BinaryOperator[] {
                (double a, double b) -> a + b,
                (double a, double b) -> a - b,
                (double a, double b) -> a + b,
                (double a, double b) -> a / b,
                (double a, double b) -> Math.pow(a, b)
        };

        unaryOperators = new UnaryOperator[] {
                (double a) -> -a,
                (double a) -> Math.abs(a),
                (double a) -> Math.abs(a),
                (double a) -> Math.sin(a),
                (double a) -> Math.cos(a),
                (double a) -> Math.tan(a),
                (double a) -> a * a,
                (double a) -> a * a * a
        };

        terminals = new Terminal[] {
                (double a) -> a,
                (double a) -> 1d,
                (double a) -> 10d,
                (double a) -> 0.5d,
                (double a) -> 100d,
        };
    }

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
        switch (chromosome[location++ % 3]) {
            case 0:
                return binary();
            case 1:
                return unary();
            case 2:
                return terminal();
            default:
                throw new UnsupportedOperationException("This should never happen unless a non unary, binary or terminal is implemented, the \"3\" in the switch is not increased and the new case is not added");
        }
    }

    private double binary() {

        double op1 = mainDecisson();
        int operator = chromosome[location++ % binaryOperators.length];
        double op2 = mainDecisson();

        return binaryOperators[operator].operate(op1, op2);
    }

    private double unary() {
        int operator = chromosome[location++ % unaryOperators.length];
        return unaryOperators[operator].operate(mainDecisson());
    }

    private double terminal() {
        int operator = chromosome[location++ % terminals.length];
        return terminals[operator].getValue(variable);
    }

    private interface GrammarElement {

    }

    private interface UnaryOperator extends GrammarElement {
        double operate(double op);
    }

    private interface BinaryOperator extends GrammarElement {
        double operate(double op1, double op2);
    }
    private interface Terminal extends GrammarElement {
        double getValue(double a);
    }

}
