package hse.kpo.homework1.entity.animals.abstracts;

import hse.kpo.homework1.entity.interfaces.IAlive;
import lombok.Getter;

public abstract class Animal implements IAlive {
    @Getter
    private String name;
    private int food;
    public Animal(String name, int food) {
        this.name = name;
        this.food = food;
    }
    public String toString(){
        return "Животное: " + getName() +", Еда в день: " + getFood() + " кг";
    }
    @Override
    public int getFood() {
        return food;
    }
}
