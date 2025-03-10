package hse.kpo.bighomework1;
import hse.kpo.bighomework1.data.DataManager;
import hse.kpo.bighomework1.data.ReportFormat;
import hse.kpo.bighomework1.entity.BankAccount;
import hse.kpo.bighomework1.entity.CategoryType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class Application {
    public static void main(String[] args) throws IOException {
        Map<Integer, BankAccount> accounts = new HashMap<>();
        accounts.put(1, new BankAccount(1, "Основной счет", 1000));
        accounts.put(2, new BankAccount(2, "Сберегательный счет", 5000));

        DataManager dataManager = new DataManager();

        // Тест JSON
        String jsonFilePath = "data.json";
        dataManager.exportData(accounts, jsonFilePath,ReportFormat.JSON);
        System.out.println("JSON Exported");
        Map<Integer, BankAccount> jsonImportedData = dataManager.importData(jsonFilePath,ReportFormat.JSON);
        jsonImportedData.forEach((id, account) ->
                System.out.println("ID: " + id + ", Name: " + account.getName() + ", Balance: " + account.getBalance()));

        // Тест CSV
        String csvFilePath = "data.csv";
        dataManager.exportData(accounts, csvFilePath,ReportFormat.CSV);
        System.out.println("CSV Exported");
        Map<Integer, BankAccount> csvImportedData = dataManager.importData(csvFilePath,ReportFormat.CSV);
        csvImportedData.forEach((id, account) ->
                System.out.println("CSV -> ID: " + id + ", Name: " + account.getName()+ ", Balance: " + account.getBalance()));

        // Тест YAML
        String yamlFilePath = "data.yaml";
        dataManager.exportData(accounts, yamlFilePath,ReportFormat.YAML);
        System.out.println("YAML Exported");
        Map<Integer, BankAccount> yamlImportedData = dataManager.importData(yamlFilePath,ReportFormat.YAML);
        yamlImportedData.forEach((id, account) ->
                System.out.println("YAML -> ID: " + id + ", Name: " + account.getName() + ", Balance: " + account.getBalance()));
        //SpringApplication.run(Application.class, args);
    }
}
