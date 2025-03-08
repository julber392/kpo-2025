package hse.kpo.bighomework1.services;

import hse.kpo.bighomework1.entity.Category;
import hse.kpo.bighomework1.services.interfaces.IStorage;

import java.util.ArrayList;
import java.util.List;

public class CategoryStorage implements IStorage<Category> {
    private final List<Category> categories = new ArrayList<>();
    @Override
    public List<Category> getStorage() {
        return categories;
    }
    @Override
    public void add(Category category)
    {
        categories.add(category);
    }

    @Override
    public void delete(int index) {
        categories.remove(index);
    }
}
