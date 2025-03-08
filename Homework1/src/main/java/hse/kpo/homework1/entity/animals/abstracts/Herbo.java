package hse.kpo.bighomework1.entity.animals.abstracts;

import hse.kpo.bighomework1.entity.interfaces.Iherbivore;


public abstract class Herbo extends Animal implements Iherbivore {
    private int kindness;

    public Herbo(String name, int food, int kindness) {
        super(name, food);
        if (kindness > 10 || kindness < 0) {
            throw new IllegalArgumentException("Wrong kindness");
        }
        this.kindness = kindness;
    }

    @Override
    public boolean isFriendly() {
        return kindness > 5;
    }
}