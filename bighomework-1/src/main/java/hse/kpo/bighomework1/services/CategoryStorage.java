package hse.kpo.bighomework1.services;

import hse.kpo.bighomework1.entity.Category;
import hse.kpo.bighomework1.services.interfaces.IStorage;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class CategoryStorage implements IStorage<Category> {
    private final Map<Integer,Category> categories = new HashMap<>();
    @Override
    public Map<Integer,Category> getStorage() {
        return categories;
    }
    @Override
    public void add(Category category)
    {
        categories.put(category.getId(),category);
    }

    @Override
    public void delete(int index) {
        categories.remove(index);
    }
}
