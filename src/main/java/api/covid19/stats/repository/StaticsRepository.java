package api.covid19.stats.repository;

import java.time.LocalDate;
import java.util.List;

import api.covid19.stats.cov19data.api.dto.TotalCountryDto;
import api.covid19.stats.jooq.public_.tables.records.EntityTotalCountryRecord;
import org.jooq.Result;

/**
 * Our Repository with our statistics (Redis, SQL, NoSQL, etc)
 */
public interface StaticsRepository {

    void insertTotalCountries(List<TotalCountryDto> allTotalCountries);

    boolean containsCountry(String country);

    boolean lastDateByCountry(String country, LocalDate lastDate);

    LocalDate getLastDateByCountry(String countryCode, LocalDate lastDate);

    Result<EntityTotalCountryRecord> getMaxMinStatisticsByCountry(String country, LocalDate ldf, LocalDate ldt);
}
