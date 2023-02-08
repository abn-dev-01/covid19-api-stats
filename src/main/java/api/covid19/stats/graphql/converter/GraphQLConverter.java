package api.covid19.stats.graphql.converter;

import java.util.List;

import api.covid19.stats.graphql.dto.AttributeKeyValuePair;
import api.covid19.stats.model.StatisticsMaxMinByCountry;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class GraphQLConverter {
    private GraphQLConverter() {}

    public static List<AttributeKeyValuePair> gqlResponse(StatisticsMaxMinByCountry stats) {
        return stats.getCountryStatistics().entrySet()
                    .stream()
                    .map(m1 ->
                             AttributeKeyValuePair.builder()
                                                  .key(m1.getKey())
                                                  .value(m1.getValue())
                                                  .build()
                    )
                    .toList();
    }
}
