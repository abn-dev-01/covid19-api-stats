package api.covid19.stats.repository;

import java.time.LocalDate;
import java.util.List;

/**
 * Our Repository with our statistics (Redis, SQL, NoSQL, etc)
 */
public interface StaticsRepository {
    void findMaxMinNewCasesByCountries(List<String> countries, LocalDate dateFrom, LocalDate dateTo);
}
