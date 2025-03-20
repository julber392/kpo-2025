package analyticsTest;
import hse.kpo.bighomework1.Application;
import hse.kpo.bighomework1.analytics.BalanceCalculationCommand;
import hse.kpo.bighomework1.analytics.Command;
import hse.kpo.bighomework1.analytics.GroupByCategoryCommand;
import hse.kpo.bighomework1.analytics.TimedCommand;
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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;



public class AnalyticsTest {

    @Test
    @DisplayName("Проверка всех методов Analytic")
    void AnalyticCheck() {
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
    }
}

