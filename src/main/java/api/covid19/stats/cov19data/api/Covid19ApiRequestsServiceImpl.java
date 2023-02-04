package api.covid19.stats.cov19data.api;

import java.util.List;

import api.covid19.stats.cov19data.api.dto.StatisticsCountriesAndPeriod;
import api.covid19.stats.service.RestSSL;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Log4j2
@RequiredArgsConstructor
@Service
public class Covid19ApiRequestsServiceImpl implements Covid19ApiRequestsService {

    private final RestSSL restSSL;

    @Value("${app.url.src.base_url}")
    private String baseApiUrl;
    @Value("${app.url.src.max_min_statistics}")
    private String apiStatisticsPath;


    @Override
    public StatisticsCountriesAndPeriod getStatistics(List<String> countries, String dateFrom, String dateTo) {



        return null;
    }
}
