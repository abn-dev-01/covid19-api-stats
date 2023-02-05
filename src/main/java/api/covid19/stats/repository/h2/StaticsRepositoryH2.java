package api.covid19.stats.repository.h2;

import java.time.LocalDate;

import api.covid19.stats.repository.StaticsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@RequiredArgsConstructor
@Service
public class StaticsRepositoryH2 implements StaticsRepository {
    @Override
    public void findMaxMinNewCasesByCountry(String countryCode, LocalDate dateFrom, LocalDate dateTo) {

    }
}
