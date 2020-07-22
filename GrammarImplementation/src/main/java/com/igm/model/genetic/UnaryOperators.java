package com.igm.model.genetic;

public class UnaryOperators {

    public static final int OPERATORS = 7;

    public static double operate(double op1, int operator) {
        switch(operator % OPERATORS) {
            case 0:
                return - op1 ;
            case 1:
                return Math.abs(op1);
            case 2:
                return op1 * op1;
            case 3:
                return op1 * op1 * op1;
            case 4:
                return Math.sin(op1);
            case 5:
                return Math.cos(op1);
            case 6:
                return Math.tan(op1);
            default:
                throw new UnsupportedOperationException("operation undefined");
        }
    }
}
