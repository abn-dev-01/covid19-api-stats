package api.covid19.stats.repository.db;

import api.covid19.stats.exceptions.CountryNotFound;
import api.covid19.stats.public_.Tables;
import api.covid19.stats.public_.tables.EntityCountry;
import api.covid19.stats.public_.tables.records.EntityCountryRecord;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.jooq.DSLContext;
import org.springframework.stereotype.Service;

/**
 * COUNTRIES table.
 */
@Log4j2
@RequiredArgsConstructor
@Service
public class CountryRepository {

    private final DSLContext jooq;


    public EntityCountryRecord findCountryBySlug(String slugCountry) {
        final var country = jooq
            .selectFrom(Tables.ENTITY_COUNTRY)
            .where(EntityCountry.ENTITY_COUNTRY.SLUG.equalIgnoreCase(slugCountry))
            .fetchOne();
        if (country == null) {
            throw new CountryNotFound("Country: " + slugCountry + " not found");
        }
        return country;
    }
}
