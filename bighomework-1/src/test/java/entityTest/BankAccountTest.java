package entityTest;

import hse.kpo.bighomework1.data.ReportFormat;
import hse.kpo.bighomework1.entity.BankAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    private BankAccount bankAccount;

    @BeforeEach
    void setUp() {
        bankAccount = new BankAccount(1, "Test Account", 1000);
    }

    @Test
    @DisplayName("Создание банковского аккаунта")
    void bankAccount_ShouldBeCreatedWithCorrectValues() {
        assertAll(
                () -> assertEquals(1, bankAccount.getId(), "ID должен быть 1"),
                () -> assertEquals("Test Account", bankAccount.getName(), "Название должно совпадать"),
                () -> assertEquals(1000, bankAccount.getBalance(), "Баланс должен быть 1000")
        );
    }

    @Test
    @DisplayName("Экспорт в JSON формат")
    void export_ShouldReturnJsonData_WhenFormatIsJSON() {
        Map<String, String> data = bankAccount.export(ReportFormat.JSON);
        assertAll(
                () -> assertEquals("1", data.get("id"), "ID должен быть '1'"),
                () -> assertEquals("Test Account", data.get("name"), "Название должно быть 'Test Account'"),
                () -> assertEquals("1000", data.get("balance"), "Баланс должен быть '1000'")
        );
    }

    @Test
    @DisplayName("Экспорт в YAML формат")
    void export_ShouldReturnYamlData_WhenFormatIsYAML() {
        Map<String, String> data = bankAccount.export(ReportFormat.YAML);
        assertAll(
                () -> assertEquals("1", data.get("id"), "ID должен быть '1'"),
                () -> assertEquals("Test Account", data.get("name"), "Название должно быть 'Test Account'"),
                () -> assertEquals("1000", data.get("balance"), "Баланс должен быть '1000'")
        );
    }

    @Test
    @DisplayName("Экспорт в CSV формат")
    void export_ShouldReturnCsvData_WhenFormatIsCSV() {
        Map<String, String> data = bankAccount.export(ReportFormat.CSV);
        assertAll(
                () -> assertEquals("1", data.get("id"), "ID должен быть '1'"),
                () -> assertEquals("Test Account", data.get("name"), "Название должно быть 'Test Account'"),
                () -> assertEquals("1000", data.get("balance"), "Баланс должен быть '1000'")
        );
    }

    @Test
    @DisplayName("Неподдерживаемый формат экспорта должен вызывать исключение")
    void export_ShouldThrowException_WhenFormatIsUnsupported() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                bankAccount.export(ReportFormat.XML)
        );
        assertEquals("Не поддерживает export в данном формате", exception.getMessage());
    }
}

