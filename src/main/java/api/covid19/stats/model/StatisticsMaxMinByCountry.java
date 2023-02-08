package api.covid19.stats.model;

import java.util.HashMap;
import java.util.Map;

import api.covid19.stats.model.type.CountStatisticsByPeriod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@Builder
public class StatisticsMaxMinByCountry {
    //    @Builder.Default
    private Map<String, CountStatisticsByPeriod> countryStatistics = new HashMap<>();
}
