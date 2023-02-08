package api.covid19.stats.graphql.dto;

import api.covid19.stats.model.type.CountStatisticsByPeriod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttributeKeyValuePair {
    private String key;
    private CountStatisticsByPeriod value;
}
