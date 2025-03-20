package impExpTest;

import hse.kpo.bighomework1.data.ReportFormat;
import hse.kpo.bighomework1.data.exporter.*;
import hse.kpo.bighomework1.data.importer.CSVImporter;
import hse.kpo.bighomework1.data.importer.DataImporter;
import hse.kpo.bighomework1.data.importer.JsonImporter;
import hse.kpo.bighomework1.data.importer.YamlImporter;
import hse.kpo.bighomework1.entity.BankAccount;
import hse.kpo.bighomework1.services.BankAccountStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ImportExportTest {
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
    void dataTest() throws IOException {
        bankAccountStorage.add(testAccount1);
        bankAccountStorage.add(testAccount2);

        List<IDataExporter> dataExporterList=new ArrayList<>();
        dataExporterList.add(new JsonExporter());
        dataExporterList.add(new YamlExporter());
        dataExporterList.add(new CSVExporter());
        DataManagerExporter dataManager = new DataManagerExporter(dataExporterList);

        // Тест JSON
        String jsonFilePath = "BankAccount";
        DataImporter jsonImporter=new JsonImporter();
        dataManager.exportData(ReportFormat.JSON,bankAccountStorage.getStorage(),jsonFilePath);
        Map<String, String> jsonImportedData = jsonImporter.importData(jsonFilePath+".json");
        assertEquals(jsonImportedData.get("1"), "{balance=10000, name=Savings, id=1}");
        assertEquals(jsonImportedData.get("2"), "{balance=5000, name=Checking, id=2}");

        // Тест CSV
        String csvFilePath = "BankAccount";
        dataManager.exportData(ReportFormat.CSV,bankAccountStorage.getStorage(),csvFilePath);
        DataImporter csvImporter=new CSVImporter();
        Map<String, String> csvImportedData = csvImporter.importData(csvFilePath+".csv");
        assertEquals(csvImportedData.get("1"), "{balance=10000, name=Savings, id=1}");
        assertEquals(csvImportedData.get("2"), "{balance=5000, name=Checking, id=2}");

    }
}
