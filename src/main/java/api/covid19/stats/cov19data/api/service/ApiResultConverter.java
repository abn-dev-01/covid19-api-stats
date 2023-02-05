package api.covid19.stats.cov19data.api.service;

import api.covid19.stats.common.CommonConstant;
import api.covid19.stats.cov19data.api.dto.StatisticsCountriesAndPeriod;
import api.covid19.stats.cov19data.api.dto.TotalCountryDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@RequiredArgsConstructor
@Service
/**
 * Converted COVID19 API Results to SYSTEM StatisticsStrategy DTO.
 */
public class ApiResultConverter {

    /**
     * Converter {@link api.covid19.stats.cov19data.api.dto.TotalCountryDto} to {@link api.covid19.stats.cov19data.api.dto.StatisticsCountriesAndPeriod}
     *
     * @param apiResult
     *
     * @return
     */
    public StatisticsCountriesAndPeriod fromTotalCountryRs(TotalCountryDto apiResult) {
        final var msg = "Converter fromTotalCountryRs()";
        LOG.debug(CommonConstant.START_DEBUG, () -> msg);
        LOG.debug(" > Given API result > {}", () -> apiResult);


        LOG.debug(CommonConstant.END_DEBUG, () -> msg);
        return null;
    }
}
