package hse;

import hse.kpo.bighomework1.Application;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes= Application.class)
public class ApplicationTests {

    @Test
    @DisplayName("Тест загрузки контекста")
    void contextLoads() {

    }

    @Test
    @DisplayName("Тест загрузки контекста")
    void bankApplicationTest() {
    }

}
