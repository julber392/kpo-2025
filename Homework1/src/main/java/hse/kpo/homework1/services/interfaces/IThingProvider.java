package hse.kpo.homework1.services.interfaces;

import hse.kpo.homework1.entity.interfaces.IInventory;

import java.util.List;

public interface IThingProvider{
    List<IInventory> getInventory();
    void addThing(IInventory thing);
}
