package hse.kpo.bighomework1;
import hse.kpo.bighomework1.data.DataManager;
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
        String filePath = "data.json";

        dataManager.exportData(accounts, filePath,"json");
        Map<Integer, BankAccount> importedData = dataManager.importData(filePath,"json");

        //SpringApplication.run(Application.class, args);
    }
}
