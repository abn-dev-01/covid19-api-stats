package api.covid19.stats.graphql.validator;

import java.time.LocalDateTime;
import java.util.List;

import api.covid19.stats.exceptions.InvalidRequestRtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Log4j2
@RequiredArgsConstructor
@Service
public class StatisticsValidator {

    public void validateDate(String dateString) {
        if (StringUtils.isBlank(dateString)) {
            throw new InvalidRequestRtException("Validation failed: Date is empty.");
        }
        try {
            LocalDateTime.parse(dateString);
        } catch (Exception e) {
            throw new InvalidRequestRtException("Validation failed: Invalid date-time. " + e.getMessage());
        }
    }

    public void validateCountries(List<String> countries) {
        if (countries == null || countries.isEmpty()) {
            throw new InvalidRequestRtException("Validation failed: Invalid CountryDto. " +
                                                    "Must be at least one country code in the list. ");
        }
    }
}
