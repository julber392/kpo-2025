package facadesTest;
import hse.kpo.bighomework1.Application;
import hse.kpo.bighomework1.entity.BankAccount;
import hse.kpo.bighomework1.facades.BankAccountFacade;
import hse.kpo.bighomework1.factories.BankAccountFactory;
import hse.kpo.bighomework1.services.BankAccountStorage;
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
public class BankAccountFacadeTest {

    @Mock
    private BankAccountStorage bankAccountStorage;

    @Mock
    private BankAccountFactory bankAccountFactory;

    @InjectMocks
    private BankAccountFacade bankAccountFacade;

    private BankAccount testAccount;

    @BeforeEach
    void setUp() {
        testAccount = new BankAccount(1, "Test User", 100);
    }

    @Test
    @DisplayName("Создание нового банковского аккаунта")
    void create_ShouldAddNewAccount() {
        // Arrange
        when(bankAccountFactory.createAccount("Test User", 0)).thenReturn(testAccount);

        // Act
        bankAccountFacade.create("Test User");

        // Assert
        verify(bankAccountStorage, times(1)).add(testAccount);
    }

    @Test
    @DisplayName("Получение хранилища аккаунтов")
    void get_ShouldReturnBankAccountStorage() {
        assertNotNull(bankAccountFacade.get());
    }

    @Test
    @DisplayName("Удаление аккаунта по ID")
    void delete_ShouldRemoveAccount() {
        // Act
        bankAccountFacade.delete(1);

        // Assert
        verify(bankAccountStorage, times(1)).delete(1);
    }

    @Test
    @DisplayName("Обновление банковского аккаунта")
    void update_ShouldModifyExistingAccount() {
        // Arrange
        when(bankAccountFactory.createAccount(eq(1), eq("Updated User"), eq(500))).thenReturn(testAccount);

        // Act
        bankAccountFacade.update(1, "Updated User", 500);

        // Assert
        verify(bankAccountStorage, times(1)).add(testAccount);
    }

}


