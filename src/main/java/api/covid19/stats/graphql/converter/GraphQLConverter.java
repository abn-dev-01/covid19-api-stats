package api.covid19.stats.graphql.converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.function.Function;
import java.util.stream.Collectors;

import api.covid19.stats.cov19data.api.dto.StatisticsCountriesAndPeriod;
import api.covid19.stats.exceptions.InvalidParameterRtException;
import api.covid19.stats.graphql.dto.StatisticsRs;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Deprecated
public class GraphQLConverter {
    private GraphQLConverter() {}

    @Deprecated
    public static StatisticsRs statisticsFromGQL(StatisticsCountriesAndPeriod statisticsGQL) {
        if (statisticsGQL == null) {
            throw new InvalidParameterRtException("Empty statistics GQL");
        }
        final var maxDatesStr =
            statisticsGQL.getMaxDate().stream()
                         .map(getLocalDateStringFunction())
                         .collect(Collectors.toList());
        final var minDatesStr =
            statisticsGQL.getMinDate().stream()
                         .map(getLocalDateStringFunction())
                         .collect(Collectors.toList());
        return StatisticsRs.builder()
                           .maxCases(statisticsGQL.getMax())
                           .maxCasesDate(maxDatesStr)
                           .minCases(statisticsGQL.getMin())
                           .minCasesDate(minDatesStr)
                           .build();
    }

    private static Function<LocalDate, String> getLocalDateStringFunction() {
        return m1 -> m1.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
