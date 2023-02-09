package api.covid19.stats.cov19data.api;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import api.covid19.stats.cov19data.Covid19DataLoader;
import api.covid19.stats.cov19data.api.dto.TotalCountryDto;
import api.covid19.stats.service.RestSSL;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
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
    @MockBean
    Covid19ApiRequestsServiceImpl covid19ApiRequestsService;
    @MockBean
    Covid19ApiHttpAuthentication authHeaders;
    @MockBean
    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
    }

    @Test
    void loadStatisticsByCountryAndPeriod() {
        String cc = "china";
        var ff = LocalDate.of(2022, 1, 1);
        var tt = LocalDate.of(2022, 12, 13);

        when(restSSL.localRestTemplate()).thenReturn(restTemplateMock);
        final var url = covid19DataLoader.getStatisticsUrl(cc);
        final var entity = covid19DataLoader.getHttpEntity();

        List<TotalCountryDto> listCountryDto = new ArrayList<>();
        listCountryDto.add(TotalCountryDto.builder().build());
        listCountryDto.add(TotalCountryDto.builder().build());

        ResponseEntity<List<TotalCountryDto>> responseEntity =
            new ResponseEntity<>(listCountryDto, HttpStatusCode.valueOf(200));

        when(restTemplateMock
                 .exchange(Mockito.eq(url),
                           Mockito.eq(HttpMethod.GET),
                           Mockito.eq(entity),
                           Mockito.any(ParameterizedTypeReference.class)))
            .thenReturn(responseEntity);

        var result = covid19DataLoader.loadRawCovid19DataByCountryAndPeriod(cc, ff, tt);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(2, result.size(), "count country DTO");
    }


    @Test
    void getHttpEntity() {
        final var headers = new HttpHeaders();
        headers.add("1", "11");
        headers.add("2", "22");

        when(authHeaders.getHeaders()).thenReturn(headers);

        var entityHttp = covid19DataLoader.getHttpEntity();

        assertNotNull(entityHttp);
        assertNotNull(entityHttp.getHeaders());
        assertTrue(entityHttp.getHeaders().size() == 2);
    }

    @Test
    void testGetStatisticsUrl() {
        String country = "china";
        var url = covid19DataLoader.getStatisticsUrl(country);
        assertNotNull(url);
        assertEquals(BASE_URL + PREMIUM_PATH, url);
    }

    @Test
    void shouldProfiledProperty_overridePropertyValues() {
        String baseUrl = ((Covid19DataLoaderApiImpl) covid19DataLoader).getBaseApiUrl();
        String path = ((Covid19DataLoaderApiImpl) covid19DataLoader).getApiStatisticsPath();

        assertEquals(BASE_URL, baseUrl, "Base URL");
        assertEquals(PREMIUM_PATH, path, "Base URL");
    }
}
