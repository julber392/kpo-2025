package factoriesTest;

import hse.kpo.bighomework1.entity.Category;
import hse.kpo.bighomework1.entity.CategoryType;
import hse.kpo.bighomework1.factories.CategoryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryFactoryTest {

    private CategoryFactory categoryFactory;

    @BeforeEach
    void setUp() {
        categoryFactory = new CategoryFactory();
    }

    @Test
    @DisplayName("Создание категории с корректными параметрами")
    void createCategory_ShouldReturnCategory_WhenParametersAreValid() {
        Category category = categoryFactory.createCategory(CategoryType.INCOME, "Salary");
        assertNotNull(category, "Объект Category не должен быть null");
        assertEquals(CategoryType.INCOME, category.getType(), "Тип категории должен быть INCOME");
        assertEquals("Salary", category.getName(), "Название категории должно быть 'Salary'");
    }

    @Test
    @DisplayName("Создание категории с указанным ID")
    void createCategory_ShouldReturnCategory_WhenIdIsSpecified() {
        Category category = categoryFactory.createCategory(1, CategoryType.OUTCOME, "Groceries");
        assertEquals(1, category.getId(), "ID должен быть 1");
        assertEquals(CategoryType.OUTCOME, category.getType(), "Тип категории должен быть OUTCOME");
        assertEquals("Groceries", category.getName(), "Название категории должно быть 'Groceries'");
    }

    @Test
    @DisplayName("Создание категории с пустым именем должно вызывать исключение")
    void createCategory_ShouldThrowException_WhenNameIsBlank() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                categoryFactory.createCategory(CategoryType.INCOME, ""));
        assertEquals("Category type and name must not be null or empty", exception.getMessage());
    }

    @Test
    @DisplayName("Создание категории с null типом должно вызывать исключение")
    void createCategory_ShouldThrowException_WhenTypeIsNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                categoryFactory.createCategory(null, "Investment"));
        assertEquals("Category type and name must not be null or empty", exception.getMessage());
    }
}

