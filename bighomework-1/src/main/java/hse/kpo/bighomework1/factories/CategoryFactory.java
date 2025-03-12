package hse.kpo.bighomework1.factories;

import hse.kpo.bighomework1.entity.Category;
import hse.kpo.bighomework1.entity.CategoryType;

import java.util.concurrent.atomic.AtomicInteger;

public class CategoryFactory {
    private static final AtomicInteger idGenerator = new AtomicInteger(1);

    public Category createCategory(CategoryType type, String name) {
        return new Category(idGenerator.getAndIncrement(), type, name);
    }
}
