package servicesTest;

import hse.kpo.bighomework1.entity.*;
import hse.kpo.bighomework1.services.OperationStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

public class OperationStorageTest {
    private OperationStorage operationStorage;
    private Operation testOperation1;
    private Operation testOperation2;

    @BeforeEach
    void setUp() {
        operationStorage = new OperationStorage();
        Category testCategory = new Category(1, CategoryType.INCOME, "Salary");
        BankAccount testAccount = new BankAccount(1, "Main Account", 10000);

        testOperation1 = new Operation(1, testCategory, testAccount, 5000, "2024-03-16", "Salary payment", CategoryType.INCOME);
        testOperation2 = new Operation(2, testCategory, testAccount, 2000, "2024-03-15", "Bonus", CategoryType.INCOME);
    }

    @Test
    void testAddOperation() {
        operationStorage.add(testOperation1);
        Map<Integer, Operation> storage = operationStorage.getStorage();

        assertEquals(1, storage.size());
        assertTrue(storage.containsKey(1));
        assertEquals(testOperation1, storage.get(1));
    }

    @Test
    void testDeleteOperation() {
        operationStorage.add(testOperation1);
        operationStorage.add(testOperation2);

        operationStorage.delete(1);
        Map<Integer, Operation> storage = operationStorage.getStorage();

        assertEquals(1, storage.size());
        assertFalse(storage.containsKey(1));
        assertTrue(storage.containsKey(2));
    }

    @Test
    void testGetStorageInitiallyEmpty() {
        Map<Integer, Operation> storage = operationStorage.getStorage();
        assertTrue(storage.isEmpty());
    }
}

