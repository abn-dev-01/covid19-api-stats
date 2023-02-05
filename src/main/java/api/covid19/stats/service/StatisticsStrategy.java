package api.covid19.stats.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import api.covid19.stats.cov19data.Covid19DataLoader;
import api.covid19.stats.cov19data.api.dto.TotalCountryDto;
import api.covid19.stats.exceptions.InvalidParameterRtException;
import api.covid19.stats.repository.StaticsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import static org.apache.commons.lang3.StringUtils.isBlank;

@Log4j2
@RequiredArgsConstructor
@Service
public class StatisticsStrategy {
    private final Covid19DataLoader covid19DataLoader;
    private final StaticsRepository staticsRepository;


    public void findStatistics(List<String> countries, String dateFrom, String dateTo) {
        final var ldf = LocalDateTime.parse(dateFrom).toLocalDate();
        final var ldt = LocalDateTime.parse(dateTo).toLocalDate();

        // preparation and validation of the Repository
        countries.stream()
                 .forEach(country -> {
                     final var isCountry = staticsRepository.containsCountry(country);
                     if (!isCountry) {
                         final String countryIso2 = staticsRepository;
                         final List<TotalCountryDto> allTotalCountries =
                             covid19DataLoader.loadStatisticsByCountryAndPeriod(country, ldf, ldt);

                         final var validatedCountries =
                             allTotalCountries.stream()
                                              .filter(f1 ->
                                                          !isBlank(f1.getCountry()) && !isBlank(f1.getDate())
                                              )
                                              .map(m1 -> {
                                                  m1.setCountryCode(countryIso2);
                                                  return m1;
                                              })
                                              .collect(Collectors.toList());

                         if (validatedCountries != null && !validatedCountries.isEmpty()) {
                             staticsRepository.insertTotalCountries(validatedCountries);
                         } else {
                             throw new InvalidParameterRtException("Invalid parameters. No data found.");
                         }
                     }
                     // check dates
                     final var upToDate = staticsRepository.lastDateByCountry(country, ldt);
                     if (!upToDate) {
                         // update load
                     }
                 });

        // getting statistics

        countries.stream()
                 .forEach(country -> {
                     final var isCountry = staticsRepository.containsCountry(country);
                     if (!isCountry) {
                         covid19DataLoader.loadStatisticsByCountryAndPeriod(country, ldf, ldt);
                     }
                     // check dates
                     final var upToDate = staticsRepository.lastDateByCountry(country, ldt);
                     if (!upToDate) {
                         // update load
                     }
                 });
    }
}
