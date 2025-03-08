package hse.kpo.bighomework1.services;

import hse.kpo.bighomework1.entity.Operation;
import hse.kpo.bighomework1.services.interfaces.IStorage;

import java.util.ArrayList;
import java.util.List;

public class OperationStorage implements IStorage<Operation> {
    private final List<Operation> operations = new ArrayList<>();
    @Override
    public List<Operation> getStorage() {
        return operations;
    }

    @Override
    public void add(Operation op) {
        operations.add(op);
    }

    @Override
    public void delete(int index) {
        operations.remove(index);
    }
}
