package api.covid19.stats.cov19data.api;

import java.util.Arrays;
import java.util.List;

import api.covid19.stats.cov19data.Covid19DataLoader;
import api.covid19.stats.service.RestSSL;
import org.apache.hc.core5.http.HttpEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("mock")
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
    void loadStatisticsByCountryAndPeriod() {
        List<String> cc = Arrays.asList(new String[]{"us", "uk"});
        String ff = "2020-05-05T00:00:00";
        String tt = "2020-06-05T00:00:00";

        Mockito.when(restSSL.localRestTemplate()).thenReturn(restTemplateMock);

        var result = covid19DataLoader.loadStatisticsByCountryAndPeriod(cc, ff, tt);

        assertNotNull(result);
        assertNotNull(result.getMax());
        assertNotNull(result.getMaxDate());
        assertNotNull(result.getMin());
        assertNotNull(result.getMinDate());
    }


    @Test
    void getHttpEntity() {
        var entityHttp = covid19DataLoader.getHttpEntity();
        assertNotNull(entityHttp);
        assertTrue(entityHttp instanceof HttpEntity);
    }

    @Test
    void testGetStatisticsUrl() {
        var url = covid19DataLoader.getStatisticsUrl(country);
        assertNotNull(url);
        assertEquals(BASE_URL + PREMIUM_PATH, url);
    }

    @Test
    public void shouldProfiledProperty_overridePropertyValues() {
        String baseUrl = ((Covid19DataLoaderApiImpl) covid19DataLoader).getBaseApiUrl();
        String path = ((Covid19DataLoaderApiImpl) covid19DataLoader).getApiStatisticsPath();

        assertEquals(BASE_URL, baseUrl, "Base URL");
        assertEquals(PREMIUM_PATH, path, "Base URL");
    }
}
