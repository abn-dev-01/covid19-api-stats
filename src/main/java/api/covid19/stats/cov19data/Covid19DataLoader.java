package api.covid19.stats.cov19data;

import java.time.LocalDate;
import java.util.List;

import api.covid19.stats.cov19data.api.dto.TotalCountryDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

public interface Covid19DataLoader {

    List<TotalCountryDto> loadStatisticsByCountryAndPeriod(
        String country,
        LocalDate dateFrom,
        LocalDate dateTo
    );

    HttpEntity<HttpHeaders> getHttpEntity();

    String getStatisticsUrl(String country);
}
