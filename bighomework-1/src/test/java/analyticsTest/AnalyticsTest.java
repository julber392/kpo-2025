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

    }
}

