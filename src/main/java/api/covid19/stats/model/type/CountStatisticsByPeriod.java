package api.covid19.stats.model.type;

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
public class CountStatisticsByPeriod {
    /*
     * LocalDate List - is dates when was MAXIMUM cases (the same value).
     */
    private int maximumCases;
    @Builder.Default
    private List<String> maximumCasesDates = new ArrayList<>();
    /*
     * LocalDate List - is dates when was MINIMUM cases (the same value).
     */
    private int minimumCases;
    @Builder.Default
    private List<String> minimumCasesDates = new ArrayList<>();
}
