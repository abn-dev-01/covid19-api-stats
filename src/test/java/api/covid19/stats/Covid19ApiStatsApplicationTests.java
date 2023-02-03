package api.covid19.stats;

import api.covid19.stats.rest.StatisticRestController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Covid19ApiStatsApplicationTests {

    @Autowired
    private StatisticRestController statisticRestController;

    @Test
    void contextLoads() {
        Assertions.assertNotNull(statisticRestController);
    }
}
