package hse.kpo.bighomework1;
import hse.kpo.bighomework1.analytics.BalanceCalculationCommand;
import hse.kpo.bighomework1.analytics.Command;
import hse.kpo.bighomework1.analytics.GroupByCategoryCommand;
import hse.kpo.bighomework1.analytics.TimedCommand;
import hse.kpo.bighomework1.data.*;
import hse.kpo.bighomework1.data.exporter.*;
import hse.kpo.bighomework1.data.importer.CSVImporter;
import hse.kpo.bighomework1.data.importer.DataImporter;
import hse.kpo.bighomework1.data.importer.JsonImporter;
import hse.kpo.bighomework1.data.importer.YamlImporter;
import hse.kpo.bighomework1.entity.CategoryType;
import hse.kpo.bighomework1.facades.BankAccountFacade;
import hse.kpo.bighomework1.facades.CategoryFacade;
import hse.kpo.bighomework1.facades.OperationFacade;
import hse.kpo.bighomework1.factories.BankAccountFactory;
import hse.kpo.bighomework1.factories.CategoryFactory;
import hse.kpo.bighomework1.factories.OperationFactory;
import hse.kpo.bighomework1.services.BankAccountStorage;
import hse.kpo.bighomework1.services.CategoryStorage;
import hse.kpo.bighomework1.services.OperationStorage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.*;

@SpringBootApplication
public class Application {
    public static void main(String[] args) throws IOException {
        SpringApplication.run(Application.class, args);
        BankAccountFacade bankAccountFacade=new BankAccountFacade(new BankAccountStorage(),new BankAccountFactory());
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

        List<IDataExporter> dataExporterList=new ArrayList<>();
        dataExporterList.add(new JsonExporter());
        dataExporterList.add(new YamlExporter());
        dataExporterList.add(new CSVExporter());
        DataManagerExporter dataManager = new DataManagerExporter(dataExporterList);

        String jsonFilePath = "BankAccount";
        DataImporter jsonImporter=new JsonImporter();
        dataManager.exportData(ReportFormat.JSON,bankAccountFacade.get().getStorage(),jsonFilePath);
        Map<String, String> jsonImportedData = jsonImporter.importData(jsonFilePath+".json");


    }
}
