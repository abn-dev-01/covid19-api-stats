package api.covid19.stats.graphql.dto;

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
    private Integer maxCases;
    private List<String> maxCasesDate;
    private Integer minCases;
    private List<String> minCasesDate;
}
