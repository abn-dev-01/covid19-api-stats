package api.covid19.stats;

import api.covid19.stats.graphql.StatisticsController;
import api.covid19.stats.graphql.validator.StatisticsValidator;
import api.covid19.stats.service.StatisticsStrategy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class Covid19ApiStatsApplicationTests {

    @Autowired
    private StatisticsController statisticsController;
    @MockBean
    StatisticsValidator statisticsValidator;
    @MockBean
    StatisticsStrategy statisticsStrategy;

    @Test
    void contextLoads() {
        Assertions.assertNotNull(statisticsController);
    }
}
