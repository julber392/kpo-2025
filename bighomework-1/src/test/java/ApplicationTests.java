import hse.kpo.bighomework1.Application;
import hse.kpo.bighomework1.facades.BankAccountFacade;
import hse.kpo.bighomework1.facades.CategoryFacade;
import hse.kpo.bighomework1.facades.OperationFacade;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest(classes= Application.class)
public class ApplicationTests {
    @Autowired
    BankAccountFacade bankAccountFacade;
    @Autowired
    CategoryFacade categoryFacade;
    @Autowired
    OperationFacade operationFacade;
    @Test
    void testFacades() {
        assertNotNull(operationFacade);
    }
    @Test
    @DisplayName("Тест загрузки контекста")
    void contextLoads() {

    }

    @Test
    @DisplayName("Тест загрузки контекста")
    void bankApplicationTest() {
    }


}
