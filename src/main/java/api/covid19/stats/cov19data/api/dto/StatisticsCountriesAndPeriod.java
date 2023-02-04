package api.covid19.stats.cov19data.api.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StatisticsCountriesAndPeriod {
    Integer max;
    List<LocalDate> maxDate;
    Integer min;
    List<LocalDate> minDate;
}
