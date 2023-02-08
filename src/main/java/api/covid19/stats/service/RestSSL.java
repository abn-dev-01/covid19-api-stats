package api.covid19.stats.service;

import java.time.Duration;

import api.covid19.stats.http.CustomHttpComponentsClientHttpRequestFactory;
import api.covid19.stats.http.HttpClientOption;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.web.client.ClientHttpRequestFactorySettings;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Log4j2
@RequiredArgsConstructor
@Service
public class RestSSL {

    public RestTemplate localRestTemplate() {

        var factory = new CustomHttpComponentsClientHttpRequestFactory(
            new HttpClientOption[]{HttpClientOption.SSL},
            ClientHttpRequestFactorySettings.DEFAULTS
                .withReadTimeout(Duration.ofSeconds(15L))
                .withConnectTimeout(Duration.ofSeconds(20L))
        );

        return new RestTemplate(factory);
    }
}
