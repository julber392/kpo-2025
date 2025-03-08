package hse.kpo.bighomework1.services.interfaces;

import hse.kpo.bighomework1.entity.interfaces.IInventory;

import java.util.List;

public interface IThingProvider{
    List<IInventory> getInventory();
    void addThing(IInventory thing);
}
