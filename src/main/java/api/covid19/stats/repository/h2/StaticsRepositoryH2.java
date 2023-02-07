package api.covid19.stats.repository.h2;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import api.covid19.stats.common.CommonConstant;
import api.covid19.stats.cov19data.api.dto.TotalCountryDto;
import api.covid19.stats.public_.Tables;
import api.covid19.stats.public_.tables.records.EntityTotalCountryRecord;
import api.covid19.stats.repository.StaticsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.jooq.DSLContext;
import org.springframework.stereotype.Service;
import static api.covid19.stats.public_.tables.EntityTotalCountry.ENTITY_TOTAL_COUNTRY;

@Log4j2
@RequiredArgsConstructor
@Service
public class StaticsRepositoryH2 implements StaticsRepository {
    public static final String DATE_ISO_8601 = "yyyy-MM-dd'T'HH:mm:ssXXX";
    private final DSLContext jooq;

    @Override
    public void insertTotalCountries(List<TotalCountryDto> allTotalCountries) {
        final var totalCountriesRecords = allTotalCountries
            .stream()
            .map(tc -> {
                LocalDate date = null;
                String countryCode = "";
                try {
                    date = LocalDateTime.parse(tc.getDate(), DateTimeFormatter.ofPattern(DATE_ISO_8601))
                                        .toLocalDate();
                    countryCode = tc.getCountryCode();
                } catch (Exception e) {
                    LOG.error("Invalid format of Date record. >" + tc.getDate());
                }
                return new EntityTotalCountryRecord(
                    null, tc.getCountry(), countryCode, tc.getProvince(), tc.getCity(), tc.getCityCode(),
                    tc.getLat(), tc.getLon(), tc.getConfirmed(), tc.getDeaths(), tc.getRecovered(), tc.getActive(),
                    date, tc.getComment());
            })
            .filter(ff -> StringUtils.isNotBlank(ff.get(ENTITY_TOTAL_COUNTRY.COUNTRY_CODE)))
            .collect(Collectors.toList());

        if (totalCountriesRecords.size() == 0) {
            return;
        }
        final var inserted = jooq.batchInsert(totalCountriesRecords)
                                 .execute();
        LOG.debug(" Inserted TOTAL_COUNTRIES records >{}", inserted);
    }

    @Override
    public boolean containsCountry(String countryCode) {
        final var msg = "findMaxMinNewCasesByCountry()";
        LOG.debug(CommonConstant.START_DEBUG, () -> msg);

        final var totalCountryByCountryIso2 = jooq
            .selectFrom(Tables.ENTITY_TOTAL_COUNTRY)
            .where(ENTITY_TOTAL_COUNTRY.COUNTRY_CODE.equalIgnoreCase(countryCode))
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
            .selectFrom(Tables.ENTITY_TOTAL_COUNTRY)
            .where(ENTITY_TOTAL_COUNTRY.COUNTRY_CODE.equalIgnoreCase(countryCode))
            .orderBy(ENTITY_TOTAL_COUNTRY.DATE_STAT.desc())
            .limit(1)
            .fetchOne();

        LOG.debug(CommonConstant.END_DEBUG, () -> msg);
        return totalCountryByCountryIso2.get(ENTITY_TOTAL_COUNTRY.DATE_STAT).isAfter(lastDate);
    }

    @Override
    public LocalDate getLastDateByCountry(String countryCode, LocalDate lastDate) {
        final var msg = "getLastDateByCountry()";
        LOG.debug(CommonConstant.START_DEBUG, () -> msg);

        final var totalCountryByCountryIso2 = jooq
            .selectFrom(Tables.ENTITY_TOTAL_COUNTRY)
            .where(ENTITY_TOTAL_COUNTRY.COUNTRY_CODE.equalIgnoreCase(countryCode))
            .orderBy(ENTITY_TOTAL_COUNTRY.DATE_STAT.desc())
            .limit(1)
            .fetchOne();

        LOG.debug(CommonConstant.END_DEBUG, () -> msg);
        return totalCountryByCountryIso2.get(ENTITY_TOTAL_COUNTRY.DATE_STAT);
    }

    @Override
    public Object findByCountry(String country, LocalDate dateFrom, LocalDate dateTo) {
        return false;
    }
}
