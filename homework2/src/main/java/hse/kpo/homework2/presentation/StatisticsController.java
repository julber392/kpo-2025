package hse.kpo.homework2.presentation;

import hse.kpo.homework2.application.ZooStatisticsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/statistics")
public class StatisticsController {
    private final ZooStatisticsService statisticsService;

    public StatisticsController(ZooStatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping
    public Map<String, Object> getStatistics() {
        return statisticsService.getStatistics();
    }
}
