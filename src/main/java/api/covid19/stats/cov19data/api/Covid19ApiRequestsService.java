package api.covid19.stats.cov19data.api;

import java.util.List;

import api.covid19.stats.cov19data.api.dto.StatisticsCountriesAndPeriod;

public interface Covid19ApiRequestsService {
    StatisticsCountriesAndPeriod getStatistics(List<String> countries, String dateFrom, String dateTo);
}
