package com.igm.model.genetic;

public class Terminal {

    //This must have 1 plus the standard terminals
    public static int TERMINALS = 5;
    private static double[] terminals = new double[TERMINALS];
    static {
        terminals[0] = 1d;
        terminals[1] = 5d;
        terminals[2] = 0.5d;
        terminals[3] = 10d;
    }

    public double operate(int location, double variable) {
        terminals[terminals.length-1] = variable;
        return terminals[location % TERMINALS];
    }
}
