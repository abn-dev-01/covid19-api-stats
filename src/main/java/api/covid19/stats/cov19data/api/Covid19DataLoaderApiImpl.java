package api.covid19.stats.cov19data.api;

import java.time.LocalDate;
import java.util.List;

import api.covid19.stats.common.CommonConstant;
import api.covid19.stats.cov19data.Covid19DataLoader;
import api.covid19.stats.cov19data.api.dto.TotalCountryDto;
import api.covid19.stats.cov19data.exception.Covid19ApiAccessFailed;
import api.covid19.stats.cov19data.exception.Covid19ApiRtException;
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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

@Log4j2
@RequiredArgsConstructor
@Service
@Getter
public class Covid19DataLoaderApiImpl implements Covid19DataLoader {

    private final RestSSL restSSL;
    private final Covid19ApiRequestsServiceImpl covid19ApiRequestsService;
    private final Covid19ApiHttpAuthentication authHeaders;
    private final ObjectMapper objectMapper;

    @Value("${app.url.src.base_url}")
    private String baseApiUrl;
    @Value("${app.url.src.max_min_statistics}")
    private String apiStatisticsPath;


    /**
     * @param country  is country ISO2 code (uk, au, ua...)
     * @param dateFrom is valid date string
     * @param dateTo   is valid date string
     *
     * @return
     */
    @Override
    public List<TotalCountryDto> loadRawCovid19DataByCountryAndPeriod(
        String country,
        LocalDate dateFrom,
        LocalDate dateTo
    ) {
        final var msg = "loadStatisticsByCountryAndPeriod()";
        LOG.debug(CommonConstant.START_DEBUG, () -> msg);

        final String url = getStatisticsUrl(country);
        final HttpEntity<HttpHeaders> entityHeaders = getHttpEntity();

        var apiResult = loadTotalCountry(url, entityHeaders);

        LOG.debug(CommonConstant.END_DEBUG, () -> msg);
        return apiResult;
    }

    protected List<TotalCountryDto> loadTotalCountry(String url, HttpEntity<HttpHeaders> entityHeaders) {
        final var type = new ParameterizedTypeReference<List<TotalCountryDto>>() {};
        final ResponseEntity<List<TotalCountryDto>> result;
        try {
            result = restSSL.localRestTemplate()
                            .exchange(url, HttpMethod.GET, entityHeaders, type);
        } catch (RestClientException e) {
            throw new Covid19ApiAccessFailed("Covid19 API failed. " + e.getMessage());
        }
        if (result.getStatusCode().value() != 200) {
            throw new Covid19ApiRtException("Connection to COVID19 API failed.");
        }
        return result.getBody();
    }

    @Override
    public HttpEntity<HttpHeaders> getHttpEntity() {
        return new HttpEntity<>(authHeaders.getHeaders());
    }

    @Override
    public String getStatisticsUrl(String country) {
        final var url = baseApiUrl.concat(apiStatisticsPath.formatted(country));
        LOG.debug("API URL > {}", () -> url);
        return url;
    }
}
