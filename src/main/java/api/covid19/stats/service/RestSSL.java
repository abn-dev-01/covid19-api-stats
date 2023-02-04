package api.covid19.stats.service;

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

//    @Value("${keystore.path}")
//    private String keystorePath;

    /**
     * Creates RestTemplate for HTTPS connection between services.
     *
     * @return
     */
    public RestTemplate localRestTemplate() {
//        SSLContext sslContext = null;
//        boolean failed = false;
//        try {
//            sslContext = new SSLContextBuilder()
//                .loadTrustMaterial(ResourceUtils.getFile(keystorePath), "changeit".toCharArray())
//                .build();
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//            failed = true;
//        } catch (KeyManagementException e) {
//            e.printStackTrace();
//            failed = true;
//        } catch (KeyStoreException e) {
//            e.printStackTrace();
//            failed = true;
//        } catch (CertificateException e) {
//            e.printStackTrace();
//            failed = true;
//        } catch (IOException e) {
//            e.printStackTrace();
//            failed = true;
//        }
//        if (failed) {
//            throw new RuntimeException("RestTemplate & SSL FAILED!");
//        }
//        SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(sslContext);
//        HttpClient httpClient = MinimalHttpClient.custom()
//                                                 .setSSLSocketFactory(socketFactory)
//                                                 .build();
//        HttpClient httpClient = HttpClients.custom()
//                                           .setSSLSocketFactory(socketFactory)
//                                           .build();
//        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);

        var factory = new CustomHttpComponentsClientHttpRequestFactory(
            new HttpClientOption[]{HttpClientOption.SSL},
            ClientHttpRequestFactorySettings.DEFAULTS
        );

        return new RestTemplate(factory);
    }
}
