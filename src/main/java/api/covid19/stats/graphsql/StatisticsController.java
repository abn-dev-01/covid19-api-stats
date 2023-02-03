package api.covid19.stats.graphsql;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import api.covid19.stats.graphsql.dto.StatisticsRs;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class StatisticsController {

    @QueryMapping
    public StatisticsRs statisticsByCountryAndPeriod(
        @Argument @Valid @NotNull String dateFrom,
        @Argument @Valid @NotNull String dateTo
    ) {
        return StatisticsRs.builder()
                           .some("SOME TEXT HERE")
                           .build();
    }
}
