package api.covid19.stats.graphql;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

import api.covid19.stats.graphql.dto.StatisticsRs;
import api.covid19.stats.graphql.validator.StatisticsValidator;
import api.covid19.stats.service.StatisticsStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Log4j2
@RequiredArgsConstructor
@Controller
public class StatisticsController {

    private final StatisticsValidator statisticsValidator;
    //    private final Covid19DataLoader covid19DataLoader;
    private final StatisticsStrategy statisticsStrategy;


    @QueryMapping
    public StatisticsRs statisticsByCountryAndPeriod(
        @Argument @Valid @NotNull List<String> countries,
        @Argument @Valid @NotNull String dateFrom,
        @Argument @Valid @NotNull String dateTo
    ) {
        statisticsValidator.validateDate(dateFrom);
        statisticsValidator.validateDate(dateTo);
        statisticsValidator.validateCountries(countries);

//        final var statisticsGQL = covid19DataLoader.loadStatisticsByCountryAndPeriod(countries, dateFrom, dateTo);
        statisticsStrategy.findStatistics(countries, dateFrom, dateTo);

//        final var statistics = GraphQLConverter.statisticsFromGQL(statisticsGQL);

        return null;
    }
}
