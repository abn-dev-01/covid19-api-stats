package api.covid19.stats.graphql.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StatisticsRs {
    @Builder.Default
    List<AttributeKeyValuePair> countryStatistics = new ArrayList<>();
}
