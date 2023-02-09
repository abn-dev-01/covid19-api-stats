package api.covid19.stats.graphql;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import api.covid19.stats.cov19data.api.Covid19DataLoaderApiImpl;
import api.covid19.stats.cov19data.api.dto.StatisticsCountriesAndPeriod;
import api.covid19.stats.graphql.validator.StatisticsValidator;
import api.covid19.stats.model.StatisticsMaxMinByCountry;
import api.covid19.stats.service.StatisticsStrategy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class StatisticsStrategyControllerTest {
    @MockBean
    Covid19DataLoaderApiImpl covid19DataLoaderApi;
    @MockBean
    StatisticsStrategy statisticsStrategy;
    @Autowired
    StatisticsValidator statisticsValidator;
    @Autowired
    StatisticsController statisticsController;

    @BeforeEach
    void setUp() {
    }


    @Test
    @DisplayName("Test controller StatisticsByCountryAndPeriod - Max & Min cases.")
    void statisticsByCountryAndPeriod() {

        List<String> countries = Arrays.asList(new String[]{"uk", "us", "ua"});
        String dateFrom = "2022-12-12T11:22:33";
        String dateTo = "2022-12-22T11:22:33";

        StatisticsMaxMinByCountry statResult = new StatisticsMaxMinByCountry();

        Mockito.when(statisticsStrategy.findStatistics(countries, dateFrom, dateTo))
               .thenReturn(statResult);

        var response = statisticsController.statisticsByCountryAndPeriod(countries, dateFrom, dateTo);

        Assertions.assertNotNull(response);
    }
}
