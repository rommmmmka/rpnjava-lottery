package com.kravets.rpnjava4;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Ticket implements Serializable {
    private List<Integer> values;

    public Ticket() {
        this.values = new ArrayList<>();
        for (int i = 0; i < 10; i++)
            this.values.add((int)(Math.random() * 100) + 1);
    }

    public Ticket(List<Integer> a) {
        this.values = new ArrayList<>(a);
    }

    public List<Integer> getValues() {
        return values;
    }

    @Override
    public String toString() {
        return values.toString();
    }
}
