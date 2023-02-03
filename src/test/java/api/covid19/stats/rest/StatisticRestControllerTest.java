package api.covid19.stats.rest;

import api.covid19.stats.rest.validator.StatisticsValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

        Assertions.assertNotNull(urlStatistics);

        mvc.perform(MockMvcRequestBuilders.get(urlStatistics))
           .andExpect(status().is5xxServerError());
    }
}
