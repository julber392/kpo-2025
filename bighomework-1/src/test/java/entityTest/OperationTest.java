package entityTest;


import hse.kpo.bighomework1.data.ReportFormat;
import hse.kpo.bighomework1.entity.BankAccount;
import hse.kpo.bighomework1.entity.Category;
import hse.kpo.bighomework1.entity.CategoryType;
import hse.kpo.bighomework1.entity.Operation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class OperationTest {

    private Operation operation;
    private Category category;
    private BankAccount bankAccount;

    @BeforeEach
    void setUp() {
        category = new Category(1, CategoryType.OUTCOME, "Food");
        bankAccount = new BankAccount(1, "Main Account", 10000);
        operation = new Operation(1, category, bankAccount, 500, "2024-03-16", "Lunch payment", CategoryType.OUTCOME);
    }

    @Test
    @DisplayName("Создание операции")
    void operation_ShouldBeCreatedWithCorrectValues() {
        assertAll(
                () -> assertEquals(1, operation.getId(), "ID должен быть 1"),
                () -> assertEquals(category, operation.getCategory(), "Категория должна соответствовать"),
                () -> assertEquals(bankAccount, operation.getBankAccount(), "Банковский счет должен соответствовать"),
                () -> assertEquals(500, operation.getAmount(), "Сумма должна быть 500"),
                () -> assertEquals("2024-03-16", operation.getDate(), "Дата должна быть '2024-03-16'"),
                () -> assertEquals("Lunch payment", operation.getDescription(), "Описание должно быть 'Lunch payment'"),
                () -> assertEquals(CategoryType.OUTCOME, operation.getType(), "Тип должен быть OUTCOME")
        );
    }

    @Test
    @DisplayName("Экспорт операции в JSON формат")
    void export_ShouldReturnJsonData_WhenFormatIsJSON() {
        Map<String, String> data = operation.export(ReportFormat.JSON);
        assertAll(
                () -> assertEquals("1", data.get("id"), "ID должен быть '1'"),
                () -> assertEquals("1", data.get("category"), "ID категории должен быть '1'"),
                () -> assertEquals("1", data.get("bankAccount"), "ID счета должен быть '1'"),
                () -> assertEquals("500", data.get("amount"), "Сумма должна быть '500'"),
                () -> assertEquals("2024-03-16", data.get("date"), "Дата должна быть '2024-03-16'"),
                () -> assertEquals("Lunch payment", data.get("descrtiption"), "Описание должно быть 'Lunch payment'"),
                () -> assertEquals("РАСХОД", data.get("type"), "Тип должен быть 'РАСХОД'")
        );
    }

    @Test
    @DisplayName("Экспорт операции в YAML формат")
    void export_ShouldReturnYamlData_WhenFormatIsYAML() {
        Map<String, String> data = operation.export(ReportFormat.YAML);
        assertAll(
                () -> assertEquals("1", data.get("id"), "ID должен быть '1'"),
                () -> assertEquals("1", data.get("category"), "ID категории должен быть '1'"),
                () -> assertEquals("1", data.get("bankAccount"), "ID счета должен быть '1'"),
                () -> assertEquals("500", data.get("amount"), "Сумма должна быть '500'"),
                () -> assertEquals("2024-03-16", data.get("date"), "Дата должна быть '2024-03-16'"),
                () -> assertEquals("Lunch payment", data.get("descrtiption"), "Описание должно быть 'Lunch payment'"),
                () -> assertEquals("РАСХОД", data.get("type"), "Тип должен быть 'РАСХОД'")
        );
    }

    @Test
    @DisplayName("Экспорт операции в CSV формат")
    void export_ShouldReturnCsvData_WhenFormatIsCSV() {
        Map<String, String> data = operation.export(ReportFormat.CSV);
        assertAll(
                () -> assertEquals("1", data.get("id"), "ID должен быть '1'"),
                () -> assertEquals("1", data.get("category"), "ID категории должен быть '1'"),
                () -> assertEquals("1", data.get("bankAccount"), "ID счета должен быть '1'"),
                () -> assertEquals("500", data.get("amount"), "Сумма должна быть '500'"),
                () -> assertEquals("2024-03-16", data.get("date"), "Дата должна быть '2024-03-16'"),
                () -> assertEquals("Lunch payment", data.get("descrtiption"), "Описание должно быть 'Lunch payment'"),
                () -> assertEquals("РАСХОД", data.get("type"), "Тип должен быть 'РАСХОД'")
        );
    }

    @Test
    @DisplayName("Неподдерживаемый формат экспорта должен вызывать исключение")
    void export_ShouldThrowException_WhenFormatIsUnsupported() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                operation.export(ReportFormat.XML)
        );
        assertEquals("Не поддерживает export в данном формате", exception.getMessage());
    }
}
