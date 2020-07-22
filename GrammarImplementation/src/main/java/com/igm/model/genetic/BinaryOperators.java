package com.igm.model.genetic;

public class BinaryOperators {

    public static final int OPERATORS = 4;

    public static double operate(double op1, double op2, int operator) {
        switch(operator % OPERATORS) {
            case 0:
                return op1 + op2;
            case 1:
                return op1 - op2;
            case 2:
                return op1 * op2;
            case 3:
                return op1 / op2;
            default:
                throw new UnsupportedOperationException("operation undefined");
        }
    }
}
