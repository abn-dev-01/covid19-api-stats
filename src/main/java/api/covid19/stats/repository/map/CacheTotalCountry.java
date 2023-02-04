package api.covid19.stats.repository.map;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import api.covid19.stats.cov19data.api.dto.TotalCountry;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@RequiredArgsConstructor
@Service
public class CacheTotalCountry {

    private final Map<String, Map<String, TotalCountry>> cache;

    public CacheTotalCountry() {
        // there is only 248 countries
        cache = new ConcurrentHashMap<>(250, 0.98f);
    }

    public Map<String, Map<String, TotalCountry>> getCache() {
        return this.cache;
    }

    public boolean containsCountry(String country) {
        return this.cache.containsKey(country);
    }

    public Object getPeriod(String df, String dt) {
        return null;
    }
}
