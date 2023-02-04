package api.covid19.stats.repository.map;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import api.covid19.stats.cov19data.api.dto.Country;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@RequiredArgsConstructor
@Service
public class CacheCountry {

    private final Map<String, Country> cache;

    public CacheCountry() {
        // there is only 248 countries
        cache = new ConcurrentHashMap<>(250, 1.0f);
    }

    public Map<String, Country> getCache() {
        return this.cache;
    }

    public Country getCountry(String countryIso2) {
        return this.cache.getOrDefault(countryIso2, Country.EMPTY);
    }

    public boolean isEmpty() {
        return this.equals(Country.EMPTY);
    }

    public boolean containsCountry(String country) {
        return this.cache.containsKey(country);
    }
}
