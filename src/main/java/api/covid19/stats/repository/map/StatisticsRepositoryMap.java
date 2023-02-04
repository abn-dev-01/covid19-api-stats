package api.covid19.stats.repository.map;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import api.covid19.stats.repository.StaticsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@RequiredArgsConstructor
@Service
public class StatisticsRepositoryMap implements StaticsRepository {
    public static final DateTimeFormatter YYYY_MM_DD = DateTimeFormatter.ofPattern("yyyyMMdd");
    private final CacheTotalCountry cacheTotalCountry;

    @Override
    public void findMaxMinNewCasesByCountries(List<String> countries, LocalDate dateFrom, LocalDate dateTo) {

        var df = dateFrom.format(YYYY_MM_DD);
        var dt = dateTo.format(YYYY_MM_DD);

        countries.stream()
                 .forEach(country -> {
                     if (!cacheTotalCountry.containsCountry(country)) {
                         // load data from API
                     }
                     cacheTotalCountry.getPeriod(df,dt);
                 });
    }
}
