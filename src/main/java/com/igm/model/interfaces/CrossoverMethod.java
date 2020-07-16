package com.igm.model.interfaces;

import java.util.Map;

public interface CrossoverMethod {
    Map<Integer, Integer> getPair(int generation);
}
