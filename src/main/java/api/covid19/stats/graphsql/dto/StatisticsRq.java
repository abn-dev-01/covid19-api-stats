package api.covid19.stats.graphsql.dto;


import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StatisticsRq implements RestRequest {

    private List<String> countries;
    private String dateFrom;
    private String dateTo;
}
