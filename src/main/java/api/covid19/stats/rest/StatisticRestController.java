package api.covid19.stats.rest;

import api.covid19.stats.rest.dto.StatisticsRq;
import api.covid19.stats.rest.validator.StatisticsValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("${app.url.api.v1.statistics}")
public class StatisticRestController {

    private final StatisticsValidator statisticsValidator;

    @GetMapping()
    public Object findMaxMinCasesInPeriod(@RequestBody StatisticsRq request) {
        statisticsValidator.validateRequest(request);

        return null;
    }
}
