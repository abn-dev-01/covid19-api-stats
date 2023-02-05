package api.covid19.stats.repository;

import java.time.LocalDate;

/**
 * Our Repository with our statistics (Redis, SQL, NoSQL, etc)
 */
public interface StaticsRepository {

    void findMaxMinNewCasesByCountry(String country, LocalDate dateFrom, LocalDate dateTo);
}
