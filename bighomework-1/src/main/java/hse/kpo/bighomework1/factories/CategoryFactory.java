package hse.kpo.bighomework1.factories;

import hse.kpo.bighomework1.entity.Category;
import hse.kpo.bighomework1.entity.CategoryType;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class CategoryFactory {
    private static final AtomicInteger idGenerator = new AtomicInteger(1);
    public Category createCategory(CategoryType type, String name) {
        if (type == null || name == null || name.isBlank()) {
            throw new IllegalArgumentException("Category type and name must not be null or empty");
        }
        return new Category(idGenerator.getAndIncrement(), type, name);
    }
    public Category createCategory(Integer id, CategoryType type, String name) {
        if (id == null || type == null || name == null || name.isBlank()) {
            throw new IllegalArgumentException("Category type and name must not be null or empty");
        }
        return new Category(id, type, name);
    }

}
