package hse.kpo.bighomework1.factories;

import hse.kpo.bighomework1.entity.Category;
import hse.kpo.bighomework1.entity.CategoryType;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class CategoryFactory {


    public Category createCategory(Integer id, CategoryType type, String name) {
        if (id == null || type == null || name == null || name.isBlank()) {
            throw new IllegalArgumentException("Category type and name must not be null or empty");
        }
        return new Category(id, type, name);

    }
}
