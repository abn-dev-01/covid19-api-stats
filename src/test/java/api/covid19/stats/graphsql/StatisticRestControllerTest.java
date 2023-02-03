package api.covid19.stats.graphsql;

import api.covid19.stats.graphsql.validator.StatisticsValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(MockitoExtension.class)
@WebMvcTest({StatisticRestController.class, StatisticsValidator.class})
//@SpringBootTest
class StatisticRestControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private StatisticsValidator statisticsValidator;
    @Value("${app.url.api.v1.statistics}")
    private String urlStatistics;

    @BeforeEach
    void setUp() {
    }

    @Test
    void findMaxMinCasesInPeriod() throws Exception {

    }
}
