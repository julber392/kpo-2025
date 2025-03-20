package facadesTest;
import hse.kpo.bighomework1.entity.BankAccount;
import hse.kpo.bighomework1.entity.Category;
import hse.kpo.bighomework1.entity.CategoryType;
import hse.kpo.bighomework1.entity.Operation;
import hse.kpo.bighomework1.facades.OperationFacade;
import hse.kpo.bighomework1.factories.OperationFactory;
import hse.kpo.bighomework1.services.OperationStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OperationFacadeTest {
    @Mock
    private OperationStorage operationStorage;

    @Mock
    private OperationFactory operationFactory;

    @InjectMocks
    private OperationFacade operationFacade;

    private Operation testOperation;
    private Category testCategory;
    private BankAccount testBankAccount;

    @BeforeEach
    void setUp() {
        testCategory = new Category(1, CategoryType.OUTCOME, "Food");
        testBankAccount = new BankAccount(1, "Test Account", 1000);
        testOperation = new Operation(1, testCategory, testBankAccount, 500, "2025-03-16", "Grocery shopping", CategoryType.OUTCOME);
    }

    @Test
    @DisplayName("Создание новой операции")
    void create_ShouldAddNewOperation() {
        // Arrange
        when(operationFactory.createOperation(testCategory, testBankAccount, 500, "2025-03-16", "Grocery shopping", CategoryType.OUTCOME)).thenReturn(testOperation);

        // Act
        operationFacade.create(testCategory, testBankAccount, 500, "2025-03-16", "Grocery shopping", CategoryType.OUTCOME);

        // Assert
        verify(operationStorage, times(1)).add(testOperation);
    }

    @Test
    @DisplayName("Получение хранилища операций")
    void get_ShouldReturnOperationStorage() {
        assertNotNull(operationFacade.get());
    }

    @Test
    @DisplayName("Удаление операции по ID")
    void delete_ShouldRemoveOperation() {
        // Act
        operationFacade.delete(1);

        // Assert
        verify(operationStorage, times(1)).delete(1);
    }

    @Test
    @DisplayName("Обновление операции")
    void update_ShouldModifyExistingOperation() {
        // Arrange
        when(operationFactory.createOperation(eq(1), eq(testCategory), eq(testBankAccount), eq(700), eq("2025-03-17"), eq("Updated Description"), eq(CategoryType.INCOME))).thenReturn(testOperation);

        // Act
        operationFacade.update(1, testCategory, testBankAccount, 700, "2025-03-17", "Updated Description", CategoryType.INCOME);

        // Assert
        verify(operationStorage, times(1)).add(testOperation);
    }
}
