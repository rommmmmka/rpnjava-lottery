package com.kravets.rpnjava4;

import java.io.Serializable;
import java.util.*;

public class Ticket implements Serializable {
    private Set<Integer> values;

    public Ticket() {
        this.values = new HashSet<>();
        while (this.values.size() < 10)
            this.values.add((int)(Math.random() * 100) + 1);
    }

    public Ticket(Set<Integer> a) {
        this.values = new HashSet<>(a);
    }

    public Set<Integer> getValues() {
        return values;
    }

    @Override
    public String toString() {
        List<Integer> list = new ArrayList<>(values);
        Collections.sort(list);
        return list.toString();
    }
}
