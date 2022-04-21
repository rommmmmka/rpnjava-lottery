package com.kravets.rpnjava4;

import java.io.Serializable;
import java.util.List;

public class ReturnData implements Serializable {
    private List<Integer> correctIndexes;
    private int bestId;

    public ReturnData(List<Integer> correctIndexes, int bestId) {
        this.correctIndexes = correctIndexes;
        this.bestId = bestId;
    }

    public List<Integer> getCorrectIndexes() {
        return correctIndexes;
    }

    public void setCorrectIndexes(List<Integer> correctIndexes) {
        this.correctIndexes = correctIndexes;
    }

    public int getBestId() {
        return bestId;
    }

    public void setBestId(int bestId) {
        this.bestId = bestId;
    }

    @Override
    public String toString() {
        return "Correct indexes: " + correctIndexes + "; id:" + bestId;
    }
}
