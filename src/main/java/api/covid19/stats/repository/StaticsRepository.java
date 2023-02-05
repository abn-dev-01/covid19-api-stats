package api.covid19.stats.repository;

import java.time.LocalDate;
import java.util.List;

import api.covid19.stats.cov19data.api.dto.TotalCountryDto;

/**
 * Our Repository with our statistics (Redis, SQL, NoSQL, etc)
 */
public interface StaticsRepository {

    void insertTotalCountries(List<TotalCountryDto> allTotalCountries);

    boolean containsCountry(String country);

    boolean lastDateByCountry(String country, LocalDate lastDate);

    LocalDate getLastDateByCountry(String countryCode, LocalDate lastDate);

    Object findByCountry(String country, LocalDate dateFrom, LocalDate dateTo);
}
