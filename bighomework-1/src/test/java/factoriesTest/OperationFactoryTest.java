package factoriesTest;


import hse.kpo.bighomework1.entity.BankAccount;
import hse.kpo.bighomework1.entity.Category;
import hse.kpo.bighomework1.entity.CategoryType;
import hse.kpo.bighomework1.entity.Operation;
import hse.kpo.bighomework1.factories.OperationFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OperationFactoryTest {
    private OperationFactory operationFactory;
    private Category category;
    private BankAccount bankAccount;

    @BeforeEach
    void setUp() {
        operationFactory = new OperationFactory();
        category = new Category(1, CategoryType.INCOME, "Salary");
        bankAccount = new BankAccount(1, "Main Account", 10000);
    }

    @Test
    void createOperation_ValidData_ShouldCreateOperation() {
        Operation operation = operationFactory.createOperation(category, bankAccount, 5000, "2024-03-16", "Monthly salary", CategoryType.INCOME);
        assertNotNull(operation);
        assertEquals(category, operation.getCategory());
        assertEquals(bankAccount, operation.getBankAccount());
        assertEquals(5000, operation.getAmount());
        assertEquals("2024-03-16", operation.getDate());
        assertEquals("Monthly salary", operation.getDescription());
        assertEquals(CategoryType.INCOME, operation.getType());
    }

    @Test
    void createOperation_WithId_ShouldCreateOperation() {
        Operation operation = operationFactory.createOperation(10, category, bankAccount, 3000, "2024-03-16", "Freelance work", CategoryType.INCOME);
        assertNotNull(operation);
        assertEquals(10, operation.getId());
        assertEquals(category, operation.getCategory());
        assertEquals(bankAccount, operation.getBankAccount());
        assertEquals(3000, operation.getAmount());
        assertEquals("2024-03-16", operation.getDate());
        assertEquals("Freelance work", operation.getDescription());
        assertEquals(CategoryType.INCOME, operation.getType());
    }

    @Test
    void createOperation_NullFields_ShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> operationFactory.createOperation(null, bankAccount, 5000, "2024-03-16", "Test", CategoryType.INCOME));
        assertThrows(IllegalArgumentException.class, () -> operationFactory.createOperation(category, null, 5000, "2024-03-16", "Test", CategoryType.INCOME));
        assertThrows(IllegalArgumentException.class, () -> operationFactory.createOperation(category, bankAccount, null, "2024-03-16", "Test", CategoryType.INCOME));
        assertThrows(IllegalArgumentException.class, () -> operationFactory.createOperation(category, bankAccount, 5000, null, "Test", CategoryType.INCOME));
        assertThrows(IllegalArgumentException.class, () -> operationFactory.createOperation(category, bankAccount, 5000, "2024-03-16", null, CategoryType.INCOME));
        assertThrows(IllegalArgumentException.class, () -> operationFactory.createOperation(category, bankAccount, 5000, "2024-03-16", "Test", null));
    }

    @Test
    void createOperation_NegativeAmount_ShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> operationFactory.createOperation(category, bankAccount, -5000, "2024-03-16", "Test", CategoryType.INCOME));
    }
}

