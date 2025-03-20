package hse.kpo.bighomework1.services;

import hse.kpo.bighomework1.entity.interfaces.IInventory;
import hse.kpo.bighomework1.services.interfaces.IThingProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ThingStorage implements IThingProvider {
    List<IInventory> inventory = new ArrayList<>();
    @Override
    public List<IInventory> getInventory() {
        return inventory;
    }
    @Override
    public void addThing(IInventory thing) {
        thing.setNumber(inventory.size());
        inventory.add(thing);
        System.out.println(thing);
    }
}
