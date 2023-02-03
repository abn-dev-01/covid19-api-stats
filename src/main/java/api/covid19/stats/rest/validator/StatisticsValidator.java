package api.covid19.stats.rest.validator;

import api.covid19.stats.exceptions.InvalidRestRequestRtException;
import api.covid19.stats.rest.dto.RestRequest;
import api.covid19.stats.rest.dto.StatisticsRq;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Log4j2
@RequiredArgsConstructor
@Service
public class StatisticsValidator implements RestValidator {

    @Override
    public void validateRequest(RestRequest request) {
        if (request == null || !(request instanceof StatisticsRq)) {
            throw new InvalidRestRequestRtException("Invalid rest request.");
        }
        StatisticsRq rq = (StatisticsRq) request;
        if (rq.getCountries() == null || rq.getCountries().isEmpty()) {
            throw new InvalidRestRequestRtException("Undefined list of countries.");
        }
        if (StringUtils.isBlank(rq.getDateFrom())) {
            throw new InvalidRestRequestRtException("Undefined Date from.");
        }
        if (StringUtils.isBlank(rq.getDateTo())) {
            throw new InvalidRestRequestRtException("Undefined Date to.");
        }
    }
}
