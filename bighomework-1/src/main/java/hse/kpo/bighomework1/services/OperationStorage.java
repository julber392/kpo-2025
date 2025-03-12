package hse.kpo.bighomework1.services;

import hse.kpo.bighomework1.entity.Operation;
import hse.kpo.bighomework1.services.interfaces.IStorage;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class OperationStorage implements IStorage<Operation> {
    private final Map<Integer,Operation> operations = new HashMap<>();
    private static final AtomicInteger idGenerator = new AtomicInteger(1);
    @Override
    public Map<Integer,Operation> getStorage() {
        return operations;
    }

    @Override
    public void add(Operation op) {
        operations.put(op.getId(), op);
    }

    @Override
    public void delete(int index) {
        operations.remove(index);
    }
}
