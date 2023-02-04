package api.covid19.stats.cov19data.api;

import java.nio.charset.Charset;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.hc.client5.http.utils.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Log4j2
@RequiredArgsConstructor
@Service
public class Covid19ApiHttpAuthentication {

    @Value("${app.url.src.username}")
    private String username;
    @Value("${app.url.src.auth}")
    private String atuh;

//    TODO DELME
//    @Value("${app.url.src.x-access-token}")
//    private String xAccessToken;


    public HttpHeaders getHeaders() {

        final var headers = new HttpHeaders();

// FIXME
//        headers.add("X-Access-Token", xAccessToken);

        String auth = username + ":" + atuh;
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
        String authHeader = "Basic " + new String(encodedAuth);
        headers.add("Authorization", authHeader);

        headers.add("Cache-Control", "no-cache");

        LOG.debug(" > COVID19 API auth headers > {}", () -> headers);
        return headers;
    }
}
