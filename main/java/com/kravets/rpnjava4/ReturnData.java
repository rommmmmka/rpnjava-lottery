package com.kravets.rpnjava4;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public class ReturnData implements Serializable {
    private Set<Integer> correct;
    private int bestId;

    public ReturnData(Set<Integer> correct, int bestId) {
        this.correct = correct;
        this.bestId = bestId;
    }

    public Set<Integer> getCorrect() {
        return correct;
    }

    public int getBestId() {
        return bestId;
    }

    @Override
    public String toString() {
        return "Correct numbers: " + correct + ", id: " + bestId;
    }
}
