package hse.kpo.homework2.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import hse.kpo.homework2.application.FeedingOrganizationService;
import hse.kpo.homework2.application.FeedingScheduleRepository;
import hse.kpo.homework2.domain.FeedingSchedule;
import hse.kpo.homework2.domain.valueObjects.FoodType;
import hse.kpo.homework2.presentation.dto.ScheduleDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FeedingScheduleController.class)
class FeedingScheduleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FeedingScheduleRepository scheduleRepository;

    @MockBean
    private FeedingOrganizationService feedingService;

    private final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    @Test
    void createSchedule_shouldReturnSchedule() throws Exception {
        // Arrange
        UUID animalId = UUID.randomUUID();
        LocalTime feedingTime = LocalTime.of(14, 30);
        ScheduleDto dto = new ScheduleDto(animalId, feedingTime, "Meat");

        FeedingSchedule expectedSchedule = new FeedingSchedule(animalId, feedingTime, new FoodType("Meat"));

        when(scheduleRepository.findAll()).thenReturn(List.of(expectedSchedule));

        // Act & Assert
        mockMvc.perform(post("/api/feeding-schedules")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.animalId").value(animalId.toString()))
                .andExpect(jsonPath("$.feedingTime").value("14:30:00"))
                .andExpect(jsonPath("$.foodType.value").value("Meat"))
                .andExpect(jsonPath("$.completed").value(false)); // 🛠️ fixed key
    }

    @Test
    void getAllSchedules_shouldReturnList() throws Exception {
        UUID animalId = UUID.randomUUID();
        FeedingSchedule schedule = new FeedingSchedule(animalId, LocalTime.of(9, 0), new FoodType("Fish"));

        when(scheduleRepository.findAll()).thenReturn(List.of(schedule));

        mockMvc.perform(get("/api/feeding-schedules"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].animalId").value(animalId.toString()))
                .andExpect(jsonPath("$[0].foodType.value").value("Fish"))
                .andExpect(jsonPath("$[0].completed").value(false)); // 🛠️ fixed key
    }

    @Test
    void completeFeeding_shouldInvokeService() throws Exception {
        UUID scheduleId = UUID.randomUUID();

        mockMvc.perform(post("/api/feeding-schedules/" + scheduleId + "/complete"))
                .andExpect(status().isOk());

        verify(feedingService).completeFeeding(scheduleId);
    }
}
