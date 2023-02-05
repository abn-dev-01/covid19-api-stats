package api.covid19.stats.repository.h2;

import java.time.LocalDate;
import java.util.List;

import api.covid19.stats.common.CommonConstant;
import api.covid19.stats.cov19data.api.dto.TotalCountryDto;
import api.covid19.stats.public_.Tables;
import api.covid19.stats.repository.StaticsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.jooq.DSLContext;
import org.springframework.stereotype.Service;
import static api.covid19.stats.public_.tables.TotalCountries.TOTAL_COUNTRIES;

@Log4j2
@RequiredArgsConstructor
@Service
public class StaticsRepositoryH2 implements StaticsRepository {
    private final DSLContext jooq;

    @Override
    public void insertTotalCountries(List<TotalCountryDto> allTotalCountries) {
        final var inserted = jooq.insertInto(Tables.TOTAL_COUNTRIES)
                                 .values(allTotalCountries)
                                 .execute();
        LOG.debug(" Inserted TOTAL_COUNTRIES records >{}", inserted);
    }

    @Override
    public boolean containsCountry(String countryCode) {
        final var msg = "findMaxMinNewCasesByCountry()";
        LOG.debug(CommonConstant.START_DEBUG, () -> msg);

        final var totalCountryByCountryIso2 = jooq
            .selectFrom(Tables.TOTAL_COUNTRIES)
            .where(TOTAL_COUNTRIES.COUNTRY_CODE.equalIgnoreCase(countryCode))
            .limit(1)
            .fetch();

        LOG.debug(CommonConstant.END_DEBUG, () -> msg);
        return totalCountryByCountryIso2.size() == 1;
    }

    /**
     * Get record from DB by CountryDto and `dateTo` and compare them.
     * If `dateTo` is after last date in DB return false.
     *
     * @param countryCode
     * @param lastDate
     *
     * @return
     */
    @Override
    public boolean lastDateByCountry(String countryCode, LocalDate lastDate) {
        final var msg = "lastDateByCountry()";
        LOG.debug(CommonConstant.START_DEBUG, () -> msg);

        final var totalCountryByCountryIso2 = jooq
            .selectFrom(Tables.TOTAL_COUNTRIES)
            .where(TOTAL_COUNTRIES.COUNTRY_CODE.equalIgnoreCase(countryCode))
            .orderBy(TOTAL_COUNTRIES.DATE_STAT.desc())
            .limit(1)
            .fetchOne();

        LOG.debug(CommonConstant.END_DEBUG, () -> msg);
        return totalCountryByCountryIso2.get(TOTAL_COUNTRIES.DATE_STAT).isAfter(lastDate);
    }

    @Override
    public LocalDate getLastDateByCountry(String countryCode, LocalDate lastDate) {
        final var msg = "getLastDateByCountry()";
        LOG.debug(CommonConstant.START_DEBUG, () -> msg);

        final var totalCountryByCountryIso2 = jooq
            .selectFrom(Tables.TOTAL_COUNTRIES)
            .where(TOTAL_COUNTRIES.COUNTRY_CODE.equalIgnoreCase(countryCode))
            .orderBy(TOTAL_COUNTRIES.DATE_STAT.desc())
            .limit(1)
            .fetchOne();

        LOG.debug(CommonConstant.END_DEBUG, () -> msg);
        return totalCountryByCountryIso2.get(TOTAL_COUNTRIES.DATE_STAT);
    }

    @Override
    public Object findByCountry(String country, LocalDate dateFrom, LocalDate dateTo) {
        return false;
    }
}
