package factoriesTest;

import hse.kpo.bighomework1.entity.BankAccount;
import hse.kpo.bighomework1.factories.BankAccountFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BankAccountFactoryTest {

    private BankAccountFactory bankAccountFactory;

    @BeforeEach
    void setUp() {
        bankAccountFactory = new BankAccountFactory();
    }

    @Test
    @DisplayName("Создание банковского счета с корректными параметрами")
    void createAccount_ShouldReturnBankAccount_WhenParametersAreValid() {
        BankAccount account = bankAccountFactory.createAccount("Savings", 10000);
        assertNotNull(account, "Объект BankAccount не должен быть null");
        assertEquals("Savings", account.getName(), "Название счета должно быть 'Savings'");
        assertEquals(10000, account.getBalance(), "Баланс должен быть 10000");
    }

    @Test
    @DisplayName("Создание банковского счета с указанным ID")
    void createAccount_ShouldReturnBankAccount_WhenIdIsSpecified() {
        BankAccount account = bankAccountFactory.createAccount(1, "Savings", 10000);
        assertEquals(1, account.getId(), "ID должен быть 1");
        assertEquals("Savings", account.getName(), "Название счета должно быть 'Savings'");
        assertEquals(10000, account.getBalance(), "Баланс должен быть 10000");
    }

    @Test
    @DisplayName("Создание банковского счета с пустым именем должно вызывать исключение")
    void createAccount_ShouldThrowException_WhenNameIsBlank() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                bankAccountFactory.createAccount("", 10000));
        assertEquals("Category type and name must not be null or empty", exception.getMessage());
    }

    @Test
    @DisplayName("Создание банковского счета с отрицательным балансом должно вызывать исключение")
    void createAccount_ShouldThrowException_WhenBalanceIsNegative() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                bankAccountFactory.createAccount("Savings", -500));
        assertEquals("Balance must be zero or greater than zero", exception.getMessage());
    }
}
