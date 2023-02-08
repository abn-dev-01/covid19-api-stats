package api.covid19.stats.cov19data.api;

import java.time.LocalDate;

import api.covid19.stats.cov19data.Covid19DataLoader;
import api.covid19.stats.service.RestSSL;
import org.apache.hc.core5.http.HttpEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("mock")
@Disabled
class Covid19DataLoaderApiImplTest {

    public static final String BASE_URL = "BASE_URL";
    public static final String PREMIUM_PATH = "PREMIUM_PATH";
    @Autowired
    Covid19DataLoader covid19DataLoader;
    @MockBean
    RestSSL restSSL;
    @MockBean
    RestTemplate restTemplateMock;

    @BeforeEach
    void setUp() {
    }

    @Test
    @Disabled
    void loadStatisticsByCountryAndPeriod() {
        String cc = "china";
        var ff = LocalDate.of(2022, 1, 1);
        var tt = LocalDate.of(2022, 12, 13);

        Mockito.when(restSSL.localRestTemplate()).thenReturn(restTemplateMock);

        var result = covid19DataLoader.loadRawCovid19DataByCountryAndPeriod(cc, ff, tt);

        assertNotNull(result);
        assertFalse(result.isEmpty());
    }


    @Test
    @Disabled
    void getHttpEntity() {
        var entityHttp = covid19DataLoader.getHttpEntity();
        assertNotNull(entityHttp);
        assertTrue(entityHttp instanceof HttpEntity);
    }

    @Test
    @Disabled
    void testGetStatisticsUrl() {
        String country = "china";
        var url = covid19DataLoader.getStatisticsUrl(country);
        assertNotNull(url);
        assertEquals(BASE_URL + PREMIUM_PATH, url);
    }

    @Test
    @Disabled
    void shouldProfiledProperty_overridePropertyValues() {
        String baseUrl = ((Covid19DataLoaderApiImpl) covid19DataLoader).getBaseApiUrl();
        String path = ((Covid19DataLoaderApiImpl) covid19DataLoader).getApiStatisticsPath();

        assertEquals(BASE_URL, baseUrl, "Base URL");
        assertEquals(PREMIUM_PATH, path, "Base URL");
    }
}
