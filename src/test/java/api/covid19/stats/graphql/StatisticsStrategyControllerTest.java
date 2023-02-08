package api.covid19.stats.graphql;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import api.covid19.stats.cov19data.api.Covid19DataLoaderApiImpl;
import api.covid19.stats.cov19data.api.dto.StatisticsCountriesAndPeriod;
import api.covid19.stats.graphql.validator.StatisticsValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Disabled
class StatisticsStrategyControllerTest {
    @MockBean
    Covid19DataLoaderApiImpl covid19DataLoaderApi;
    @Autowired
    StatisticsValidator statisticsValidator;
    @Autowired
    StatisticsController statisticsController;

    @BeforeEach
    void setUp() {
    }


    @Test
    @Disabled
    @DisplayName("Test controller StatisticsByCountryAndPeriod - Max & Min cases.")
    void statisticsByCountryAndPeriod() {

        List<String> countries = Arrays.asList(new String[]{"uk", "us", "ua"});
        String dateFrom = "2022-12-12T11:22:33";
        String dateTo = "2022-12-22T11:22:33";

        // mocked response
        final int maxCases = 100;
        final int minCases = 2;
        StatisticsCountriesAndPeriod statGQL = new StatisticsCountriesAndPeriod(
            maxCases, Arrays.asList(new LocalDate[]{LocalDate.of(2022, 1, 1)}),
            minCases, Arrays.asList(
            new LocalDate[]{
                LocalDate.of(2022, 1, 1),
                LocalDate.of(2022, 2, 1),
                LocalDate.of(2022, 3, 1)
            })
        );

//        Mockito.when(covid19DataLoaderApi.loadStatisticsByCountryAndPeriod(countries, dateFrom, dateTo))
//               .thenReturn(statGQL);

        var response = statisticsController.statisticsByCountryAndPeriod(countries, dateFrom, dateTo);

        Assertions.assertNotNull(response);

//        assertEquals(response.getMaxCases(), maxCases, "Max cases");
//        Assertions.assertNotNull(response.getMaxCasesDate());
//        assertEquals(response.getMaxCasesDate().size(), 1, "Max cases date count");
//
//        assertEquals(response.getMinCases(), minCases, "Min cases");
//        Assertions.assertNotNull(response.getMinCasesDate());
//        assertEquals(response.getMinCasesDate().size(), 3, "Min cases date count");
    }
}
