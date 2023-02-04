package api.covid19.stats.cov19data;

import java.util.List;

import api.covid19.stats.cov19data.api.dto.StatisticsCountriesAndPeriod;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

public interface Covid19DataLoader {
    StatisticsCountriesAndPeriod loadStatisticsByCountryAndPeriod(
        List<String> countries,
        String dateFrom,
        String dateTo
    );

    HttpEntity<HttpHeaders> getHttpEntity();

    String getStatisticsUrl();
}
