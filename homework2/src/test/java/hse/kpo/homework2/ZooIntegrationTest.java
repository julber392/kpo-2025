package hse.kpo.homework2;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class ZooIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void fullFlowTest() throws Exception {
        // 🦁 1. Создаем вольер
        mockMvc.perform(post("/api/enclosures")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                                "type": "Predator",
                                "size": 100,
                                "maxCapacity": 2
                            }
                        """))
                .andExpect(status().isCreated());

        // 🐯 2. Создаем животное
        mockMvc.perform(post("/api/animals")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                                "species": "Lion",
                                "name": "Simba",
                                "birthDate": "2020-05-15",
                                "gender": "Male",
                                "favoriteFood": "Meat"
                            }
                        """))
                .andExpect(status().isCreated());

        // 📋 3. Проверяем, что животное добавилось
        mockMvc.perform(get("/api/animals"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name.value").value("Simba"));
    }
}
