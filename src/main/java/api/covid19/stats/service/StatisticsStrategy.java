package api.covid19.stats.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import api.covid19.stats.cov19data.Covid19DataLoader;
import api.covid19.stats.cov19data.api.dto.TotalCountryDto;
import api.covid19.stats.exceptions.InvalidParameterRtException;
import api.covid19.stats.exceptions.InvalidRequestRtException;
import api.covid19.stats.model.StatisticsMaxMinByCountry;
import api.covid19.stats.model.type.CountStatisticsByPeriod;
import api.covid19.stats.public_.tables.records.EntityTotalCountryRecord;
import api.covid19.stats.repository.StaticsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.jooq.Result;
import org.springframework.stereotype.Service;
import static api.covid19.stats.public_.Tables.ENTITY_TOTAL_COUNTRY;
import static org.apache.commons.lang3.StringUtils.isBlank;

@Log4j2
@RequiredArgsConstructor
@Service
public class StatisticsStrategy {
    private final Covid19DataLoader covid19DataLoader;
    private final StaticsRepository staticsRepository;

    public StatisticsMaxMinByCountry findStatistics(List<String> countries, String dateFrom, String dateTo) {
        final var ldf = LocalDateTime.parse(dateFrom).toLocalDate();
        final var ldt = LocalDateTime.parse(dateTo).toLocalDate();

        // preparation and validation of the Repository
        countries.stream()
                 .forEach(countrySlug -> {
                     final var isCountry = staticsRepository.containsCountry(countrySlug);
                     if (!isCountry) {
                         final String countryIso2 = countrySlug;
                         final List<TotalCountryDto> allTotalCountries =
                             covid19DataLoader.loadRawCovid19DataByCountryAndPeriod(countrySlug, ldf, ldt);

                         final List<TotalCountryDto> validatedCountries =
                             getFilterValidCountriesApiData(countryIso2, allTotalCountries);

                         if (validatedCountries != null && !validatedCountries.isEmpty()) {
                             staticsRepository.insertTotalCountries(validatedCountries);
                         } else {
                             throw new InvalidParameterRtException("Invalid parameters. No data found.");
                         }
                     }
                 });

        // getting statistics
        return readPreparedStatistics(countries, ldf, ldt);
    }

    private static List<TotalCountryDto> getFilterValidCountriesApiData(
        String countryIso2,
        List<TotalCountryDto> allTotalCountries
    ) {
        return allTotalCountries.stream()
                                .filter(f1 ->
                                            !isBlank(f1.getCountry()) && !isBlank(f1.getDate())
                                )
                                .map(m1 -> {
                                    m1.setCountryCode(countryIso2);
                                    return m1;
                                })
                                .toList();
    }

    private StatisticsMaxMinByCountry readPreparedStatistics(List<String> countries, LocalDate ldf, LocalDate ldt) {
        StatisticsMaxMinByCountry statMinMax = new StatisticsMaxMinByCountry();
        countries.stream()
                 .forEach(country -> {
                     final var isCountry = staticsRepository.containsCountry(country);
                     if (!isCountry) {
                         throw new InvalidRequestRtException("No data for country: `" + country + "` found.");
                     }
                     // get data from Repository
                     final var stats = staticsRepository.getMaxMinStatisticsByCountry(country, ldf.minusDays(1), ldt);
                     final var newCasesByCountry = calculatesNewCasesByCountry(stats);
                     LOG.debug(" # `{}` New Cases: {} ", () -> country, () -> newCasesByCountry);
                     statMinMax.getCountryStatistics().put(country, newCasesByCountry);
                 });
        return statMinMax;
    }

    private static CountStatisticsByPeriod calculatesNewCasesByCountry(Result<EntityTotalCountryRecord> stats) {
        CountStatisticsByPeriod countStat = new CountStatisticsByPeriod();

        if (stats != null && stats.isNotEmpty() && stats.size() > 1) {
            final var sorted = stats.sortAsc(ENTITY_TOTAL_COUNTRY.DATE_STAT);

            for (int curDay = 1; curDay < stats.size(); curDay++) {
                // 1st day is day before
                var curDate = sorted.get(curDay).get(ENTITY_TOTAL_COUNTRY.DATE_STAT);
                LOG.trace(" -- Day: {}", () -> curDate);
                int prevDay = curDay - 1;
                Integer newCasesDay = getNewCasesADay(sorted, curDay, prevDay);

                // maximum
                final String curDateStr = curDate.toString();
                calculateMaximum(countStat, newCasesDay, curDateStr);
                calculateMinimum(countStat, newCasesDay, curDateStr);
            }
        } else {
            LOG.error("Statistics not found.");
        }
        return countStat;
    }

    private static void calculateMaximum(CountStatisticsByPeriod countStat, Integer newCasesDay, String curDateStr) {
        if (countStat.getMaximumCases() < newCasesDay) {
            countStat.setMaximumCases(newCasesDay);
            countStat.getMaximumCasesDates().clear();
            countStat.getMaximumCasesDates().add(curDateStr);
        } else if (countStat.getMaximumCases() == newCasesDay) {
            countStat.getMaximumCasesDates().add(curDateStr);
        }
    }

    private static void calculateMinimum(CountStatisticsByPeriod countStat, Integer newCasesDay, String curDateStr) {
        // minimum
        if ((countStat.getMinimumCases() == 0 && newCasesDay > 0)
            || (countStat.getMinimumCases() > 0 && newCasesDay > 0 && countStat.getMinimumCases() > newCasesDay)
        ) {
            countStat.setMinimumCases(newCasesDay);
            countStat.getMinimumCasesDates().clear();
            countStat.getMinimumCasesDates().add(curDateStr);
        } else if (countStat.getMinimumCases() == newCasesDay) {
            countStat.getMinimumCasesDates().add(curDateStr);
        }
    }

    private static Integer getNewCasesADay(Result<EntityTotalCountryRecord> sorted, int curDay, int prevDay) {
        final Integer activeLastDay = sorted.get(prevDay).get(ENTITY_TOTAL_COUNTRY.ACTIVE);
        final Integer activeCurrDay = sorted.get(curDay).get(ENTITY_TOTAL_COUNTRY.ACTIVE);
        return activeCurrDay > activeLastDay ? activeCurrDay - activeLastDay : 0;
    }
}
