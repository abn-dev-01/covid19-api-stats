package api.covid19.stats.cov19data.api;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import api.covid19.stats.common.CommonConstant;
import api.covid19.stats.cov19data.Covid19DataLoader;
import api.covid19.stats.cov19data.api.dto.StatisticsCountriesAndPeriod;
import api.covid19.stats.cov19data.api.dto.TotalCountry;
import api.covid19.stats.cov19data.api.service.ApiResultConverter;
import api.covid19.stats.cov19data.exception.Covid19ApiRtException;
import api.covid19.stats.repository.StaticsRepository;
import api.covid19.stats.service.RestSSL;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

@Log4j2
@RequiredArgsConstructor
@Service
@Getter
public class Covid19DataLoaderApiImpl implements Covid19DataLoader {

    private final RestSSL restSSL;
    private final Covid19ApiRequestsServiceImpl covid19ApiRequestsService;
    private final Covid19ApiHttpAuthentication authHeaders;
    private final ApiResultConverter apiResultConverter;
    private final ObjectMapper objectMapper;
    private final StaticsRepository statisticsRepository;

    @Value("${app.url.src.base_url}")
    private String baseApiUrl;
    @Value("${app.url.src.max_min_statistics}")
    private String apiStatisticsPath;


    /**
     * @param countries
     * @param dateFrom  is valid date
     * @param dateTo    is valid date
     *
     * @return
     */
    @Override
    public StatisticsCountriesAndPeriod loadStatisticsByCountryAndPeriod(
        List<String> countries,
        String dateFrom,
        String dateTo
    ) {
        final var msg = "loadStatisticsByCountryAndPeriod()";
        LOG.debug(CommonConstant.START_DEBUG, () -> msg);

        final String url = getStatisticsUrl();

        HttpEntity<HttpHeaders> entityHeaders = getHttpEntity();
        final var ldf = LocalDateTime.parse(dateFrom);
        final var ldt = LocalDateTime.parse(dateTo);

        // Read COVID19 DATA from Repository
        prepareAllCountriesStatistics(countries, ldf.toLocalDate(), ldt.toLocalDate());

        LOG.info(" > Starting request to COVID19 API >");
        var apiResult = loadTotalCountry(url, entityHeaders);
        LOG.info(" > End of request to COVID19 API >");

        StatisticsCountriesAndPeriod response = apiResultConverter.fromTotalCountryRs(apiResult);

        LOG.debug(CommonConstant.END_DEBUG, () -> msg);
        return response;
    }

    protected void prepareAllCountriesStatistics(
        List<String> countries,
        LocalDate localDateFrom,
        LocalDate localDateTo
    ) {
        countries.stream()
                 .forEach(countryCode -> {
                     // FIXME if we can then will run it in concurrent
                     statisticsRepository.findMaxMinNewCasesByCountry(countryCode, localDateFrom, localDateTo);
                 });
    }

    private TotalCountry loadTotalCountry(String url, HttpEntity<HttpHeaders> entityHeaders) {

        final var type = new ParameterizedTypeReference<List<TotalCountry>>() {
        };
        final var result = restSSL.localRestTemplate()
                                  .exchange(url, HttpMethod.GET, entityHeaders, type);
        LOG.debug(" get data from API > {}", () -> result);
        if (result.getStatusCode().value() != 200) {
            throw new Covid19ApiRtException("Connection to COVID19 API failed.");
        }

        // update Repository
//        return resultApi != null ? resultApi : null;
        return null;
    }

    @Override
    public HttpEntity<HttpHeaders> getHttpEntity() {
        var entityHeaders = new HttpEntity<HttpHeaders>(authHeaders.getHeaders());
        return entityHeaders;
    }

    @Override
    public String getStatisticsUrl() {
        var country = "uk";
        final var url = baseApiUrl.concat(apiStatisticsPath.formatted(country));
        LOG.debug("API URL > {}", () -> url);
        return url;
    }
}
