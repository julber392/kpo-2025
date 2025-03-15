package hse.kpo.bighomework1;
import hse.kpo.bighomework1.data.*;
import hse.kpo.bighomework1.data.exporter.*;
import hse.kpo.bighomework1.entity.BankAccount;
import hse.kpo.bighomework1.entity.Category;
import hse.kpo.bighomework1.entity.CategoryType;
import hse.kpo.bighomework1.entity.Operation;
import hse.kpo.bighomework1.facades.BankAccountFacade;
import hse.kpo.bighomework1.facades.CategoryFacade;
import hse.kpo.bighomework1.facades.OperationFacade;
import hse.kpo.bighomework1.factories.BankAccountFactory;
import hse.kpo.bighomework1.factories.CategoryFactory;
import hse.kpo.bighomework1.factories.OperationFactory;
import hse.kpo.bighomework1.services.BankAccountStorage;
import hse.kpo.bighomework1.services.CategoryStorage;
import hse.kpo.bighomework1.services.OperationStorage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import hse.kpo.bighomework1.analytics.*;
import java.io.IOException;
import java.util.*;

@SpringBootApplication
public class Application {
    public static void main(String[] args) throws IOException {
        BankAccountFacade bankAccountFacade=new BankAccountFacade(new BankAccountStorage(),new BankAccountFactory());

        bankAccountFacade.create("Основной счет");
        bankAccountFacade.update(1,"Основной счет",1000);
        bankAccountFacade.create("Сберегательный счет");
        bankAccountFacade.update(2,"Основной счет",5000);

        List<IDataExporter> dataExporterList=new ArrayList<>();
        dataExporterList.add(new JsonExporter());
        dataExporterList.add(new YamlExporter());
        dataExporterList.add(new CSVExporter());
        DataManagerExporter dataManager = new DataManagerExporter(dataExporterList);

        dataManager.exportData(ReportFormat.JSON,bankAccountFacade.get().getStorage(),"BankAccount");
        DataImporter importer = new CSVImporter();
        importer.importData("BankAccount.csv");

        importer = new JsonImporter();
        importer.importData("BankAccount.json");

        importer = new YamlImporter();
        importer.importData("BankAccount.yaml");


        /*BankAccountFacade bankAccountFacade=new BankAccountFacade(new BankAccountStorage(),new BankAccountFactory());
        CategoryFacade categoryFacade=new CategoryFacade(new CategoryStorage(),new CategoryFactory());
        OperationFacade operationFacade=new OperationFacade(new OperationStorage(),new OperationFactory());

        bankAccountFacade.create("Основной счет1");
        bankAccountFacade.create("Основной счет2");
        bankAccountFacade.create("Сберегательный счет1");
        bankAccountFacade.create("Основной счет3");
        bankAccountFacade.create("Основной счет4");
        bankAccountFacade.create("Сберегательный счет2");
        bankAccountFacade.create("Основной счет5");
        bankAccountFacade.create("Основной счет6");
        bankAccountFacade.create("Сберегательный счет3");

        categoryFacade.create(CategoryType.OUTCOME,"Кафе");
        categoryFacade.create(CategoryType.OUTCOME,"Здоровье");
        categoryFacade.create(CategoryType.OUTCOME,"Отдых");
        categoryFacade.create(CategoryType.INCOME,"Зарплата");

        operationFacade.create(categoryFacade.get().getStorage().get(1),bankAccountFacade.get().getStorage().get(1),
                1000,"10.10.2003","Сеть кафе",categoryFacade.get().getStorage().get(1).getType());

        operationFacade.create(categoryFacade.get().getStorage().get(2),bankAccountFacade.get().getStorage().get(1),
                1500,"10.11.2003","Сеть кафе",categoryFacade.get().getStorage().get(2).getType());

        operationFacade.create(categoryFacade.get().getStorage().get(1),bankAccountFacade.get().getStorage().get(1),
                1000,"10.10.2003","Сеть кафе",categoryFacade.get().getStorage().get(1).getType());

        operationFacade.create(categoryFacade.get().getStorage().get(4),bankAccountFacade.get().getStorage().get(2),
                3000,"10.10.2013","",categoryFacade.get().getStorage().get(4).getType());

        operationFacade.create(categoryFacade.get().getStorage().get(1),bankAccountFacade.get().getStorage().get(3),
                1000,"10.10.2003","Сеть кафе",categoryFacade.get().getStorage().get(1).getType());

        Command balanceCommand = new TimedCommand(new BalanceCalculationCommand(operationFacade.get()));
        Command categoryCommand = new TimedCommand(new GroupByCategoryCommand(operationFacade.get(), categoryFacade.get()));
        balanceCommand.execute();
        categoryCommand.execute();
        /*Map<Integer, BankAccount> accounts = new HashMap<>();
        accounts.put(1, new BankAccount(1, "Основной счет", 1000));
        accounts.put(2, new BankAccount(2, "Сберегательный счет", 5000));

        List<IDataExporter> dataExporterList=new ArrayList<>();
        dataExporterList.add(new JsonExporter());
        dataExporterList.add(new YamlExporter());
        dataExporterList.add(new CSVExporter());
        DataManagerExporter dataManager = new DataManagerExporter(dataExporterList);




        // Тест JSON
        String jsonFilePath = "BankAccount";
        dataManager.exportData(ReportFormat.CSV,accounts,jsonFilePath);
        System.out.println("CSV Exported");
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
        //SpringApplication.run(Application.class, args);*/
    }
}
