package servicesTest;

import hse.kpo.bighomework1.entity.Category;
import hse.kpo.bighomework1.entity.CategoryType;
import hse.kpo.bighomework1.services.CategoryStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

public class CategoryStorageTest {
    private CategoryStorage categoryStorage;
    private Category testCategory1;
    private Category testCategory2;

    @BeforeEach
    void setUp() {
        categoryStorage = new CategoryStorage();
        testCategory1 = new Category(1, CategoryType.INCOME, "Salary");
        testCategory2 = new Category(2, CategoryType.OUTCOME, "Groceries");
    }

    @Test
    void testAddCategory() {
        categoryStorage.add(testCategory1);
        Map<Integer, Category> storage = categoryStorage.getStorage();

        assertEquals(1, storage.size());
        assertTrue(storage.containsKey(1));
        assertEquals(testCategory1, storage.get(1));
    }

    @Test
    void testDeleteCategory() {
        categoryStorage.add(testCategory1);
        categoryStorage.add(testCategory2);

        categoryStorage.delete(1);
        Map<Integer, Category> storage = categoryStorage.getStorage();

        assertEquals(1, storage.size());
        assertFalse(storage.containsKey(1));
        assertTrue(storage.containsKey(2));
    }

    @Test
    void testGetStorageInitiallyEmpty() {
        Map<Integer, Category> storage = categoryStorage.getStorage();
        assertTrue(storage.isEmpty());
    }
}

