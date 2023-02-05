package api.covid19.stats.repository.h2;

import api.covid19.stats.public_.Tables.*;
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


    public Countries  findCountryBySlug(String slugCountry) {
        return jooq
            .selectFrom(Tables.COUNTRIES)
            .where(Countries.COUNTRIES.SLUG.equalIgnoreCase(slugCountry))
            .fetchOne();
    }
}
