package servicesTest;

import hse.kpo.bighomework1.entity.BankAccount;
import hse.kpo.bighomework1.services.BankAccountStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

public class BankAccountStorageTest {
    private BankAccountStorage bankAccountStorage;
    private BankAccount testAccount1;
    private BankAccount testAccount2;

    @BeforeEach
    void setUp() {
        bankAccountStorage = new BankAccountStorage();
        testAccount1 = new BankAccount(1, "Savings", 10000);
        testAccount2 = new BankAccount(2, "Checking", 5000);
    }

    @Test
    void testAddBankAccount() {
        bankAccountStorage.add(testAccount1);
        Map<Integer, BankAccount> storage = bankAccountStorage.getStorage();

        assertEquals(1, storage.size());
        assertTrue(storage.containsKey(1));
        assertEquals(testAccount1, storage.get(1));
    }

    @Test
    void testDeleteBankAccount() {
        bankAccountStorage.add(testAccount1);
        bankAccountStorage.add(testAccount2);

        bankAccountStorage.delete(1);
        Map<Integer, BankAccount> storage = bankAccountStorage.getStorage();

        assertEquals(1, storage.size());
        assertFalse(storage.containsKey(1));
        assertTrue(storage.containsKey(2));
    }

    @Test
    void testGetStorageInitiallyEmpty() {
        Map<Integer, BankAccount> storage = bankAccountStorage.getStorage();
        assertTrue(storage.isEmpty());
    }
}

