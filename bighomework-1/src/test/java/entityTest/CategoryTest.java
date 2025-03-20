package entityTest;

import hse.kpo.bighomework1.data.ReportFormat;
import hse.kpo.bighomework1.entity.Category;
import hse.kpo.bighomework1.entity.CategoryType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryTest {

    private Category category;

    @BeforeEach
    void setUp() {
        category = new Category(1, CategoryType.OUTCOME, "Food");
    }

    @Test
    @DisplayName("Создание категории")
    void category_ShouldBeCreatedWithCorrectValues() {
        assertAll(
                () -> assertEquals(1, category.getId(), "ID должен быть 1"),
                () -> assertEquals(CategoryType.OUTCOME, category.getType(), "Тип должен быть OUTCOME"),
                () -> assertEquals("Food", category.getName(), "Название должно быть 'Food'")
        );
    }

    @Test
    @DisplayName("Экспорт в JSON формат")
    void export_ShouldReturnJsonData_WhenFormatIsJSON() {
        Map<String, String> data = category.export(ReportFormat.JSON);
        assertAll(
                () -> assertEquals("1", data.get("id"), "ID должен быть '1'"),
                () -> assertEquals("РАСХОД", data.get("type"), "Тип должен быть 'РАСХОД'"),
                () -> assertEquals("Food", data.get("name"), "Название должно быть 'Food'")
        );
    }

    @Test
    @DisplayName("Экспорт в YAML формат")
    void export_ShouldReturnYamlData_WhenFormatIsYAML() {
        Map<String, String> data = category.export(ReportFormat.YAML);
        assertAll(
                () -> assertEquals("1", data.get("id"), "ID должен быть '1'"),
                () -> assertEquals("РАСХОД", data.get("type"), "Тип должен быть 'РАСХОД'"),
                () -> assertEquals("Food", data.get("name"), "Название должно быть 'Food'")
        );
    }

    @Test
    @DisplayName("Экспорт в CSV формат")
    void export_ShouldReturnCsvData_WhenFormatIsCSV() {
        Map<String, String> data = category.export(ReportFormat.CSV);
        assertAll(
                () -> assertEquals("1", data.get("id"), "ID должен быть '1'"),
                () -> assertEquals("РАСХОД", data.get("type"), "Тип должен быть 'РАСХОД'"),
                () -> assertEquals("Food", data.get("name"), "Название должно быть 'Food'")
        );
    }

    @Test
    @DisplayName("Неподдерживаемый формат экспорта должен вызывать исключение")
    void export_ShouldThrowException_WhenFormatIsUnsupported() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                category.export(ReportFormat.XML)
        );
        assertEquals("Не поддерживает export в данном формате", exception.getMessage());
    }
}
