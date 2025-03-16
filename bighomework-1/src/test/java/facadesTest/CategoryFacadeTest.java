package facadesTest;
import hse.kpo.bighomework1.entity.Category;
import hse.kpo.bighomework1.entity.CategoryType;
import hse.kpo.bighomework1.facades.CategoryFacade;
import hse.kpo.bighomework1.factories.CategoryFactory;
import hse.kpo.bighomework1.services.CategoryStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoryFacadeTest {
    @Mock
    private CategoryStorage categoryStorage;

    @Mock
    private CategoryFactory categoryFactory;

    @InjectMocks
    private CategoryFacade categoryFacade;

    private Category testCategory;

    @BeforeEach
    void setUp() {
        testCategory = new Category(1, CategoryType.OUTCOME, "Food");
    }

    @Test
    @DisplayName("Создание новой категории")
    void create_ShouldAddNewCategory() {
        // Arrange
        when(categoryFactory.createCategory(CategoryType.OUTCOME, "Food")).thenReturn(testCategory);

        // Act
        categoryFacade.create(CategoryType.OUTCOME, "Food");

        // Assert
        verify(categoryStorage, times(1)).add(testCategory);
    }

    @Test
    @DisplayName("Получение хранилища категорий")
    void get_ShouldReturnCategoryStorage() {
        assertNotNull(categoryFacade.get());
    }

    @Test
    @DisplayName("Удаление категории по ID")
    void delete_ShouldRemoveCategory() {
        // Act
        categoryFacade.delete(1);

        // Assert
        verify(categoryStorage, times(1)).delete(1);
    }

    @Test
    @DisplayName("Обновление категории")
    void update_ShouldModifyExistingCategory() {
        // Arrange
        when(categoryFactory.createCategory(eq(1), eq(CategoryType.INCOME), eq("Salary"))).thenReturn(testCategory);

        // Act
        categoryFacade.update(1, CategoryType.INCOME, "Salary");

        // Assert
        verify(categoryStorage, times(1)).add(testCategory);
    }
}
