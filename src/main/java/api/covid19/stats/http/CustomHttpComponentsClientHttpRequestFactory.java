package api.covid19.stats.http;

import javax.net.ssl.SSLContext;
import java.net.URI;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.cookie.StandardCookieSpec;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.client5.http.protocol.HttpClientContext;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactoryBuilder;
import org.apache.hc.client5.http.ssl.TrustSelfSignedStrategy;
import org.apache.hc.core5.http.io.SocketConfig;
import org.apache.hc.core5.http.protocol.HttpContext;
import org.apache.hc.core5.http.ssl.TLS;
import org.apache.hc.core5.ssl.SSLContextBuilder;
import org.springframework.boot.web.client.ClientHttpRequestFactorySettings;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

public class CustomHttpComponentsClientHttpRequestFactory extends HttpComponentsClientHttpRequestFactory {

//protected static class CustomHttpComponentsClientHttpRequestFactory extends HttpComponentsClientHttpRequestFactory {

    private final String cookieSpec;

    private final boolean enableRedirects;

    public CustomHttpComponentsClientHttpRequestFactory(
        HttpClientOption[] httpClientOptions,
        ClientHttpRequestFactorySettings settings
    ) {
        Set<HttpClientOption> options = new HashSet<>(Arrays.asList(httpClientOptions));
        this.cookieSpec = (options.contains(HttpClientOption.ENABLE_COOKIES) ? StandardCookieSpec.STRICT
            : StandardCookieSpec.IGNORE);
        this.enableRedirects = options.contains(HttpClientOption.ENABLE_REDIRECTS);
        boolean ssl = options.contains(HttpClientOption.SSL);
        if (settings.readTimeout() != null || ssl) {
            setHttpClient(createHttpClient(settings.readTimeout(), ssl));
        }
        if (settings.connectTimeout() != null) {
            setConnectTimeout((int) settings.connectTimeout().toMillis());
        }
        if (settings.bufferRequestBody() != null) {
            setBufferRequestBody(settings.bufferRequestBody());
        }
    }

    //    private HttpClient createHttpClient(Duration readTimeout, boolean ssl) {
    private HttpClient createHttpClient(Duration readTimeout, boolean ssl) {
        try {
            HttpClientBuilder builder = HttpClients.custom();
            builder.setConnectionManager(createConnectionManager(readTimeout, ssl));
            builder.setDefaultRequestConfig(createRequestConfig());
            return builder.build();
        } catch (Exception ex) {
            throw new IllegalStateException("Unable to create customized HttpClient", ex);
        }
    }

    private PoolingHttpClientConnectionManager createConnectionManager(Duration readTimeout, boolean ssl)
        throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException {
        PoolingHttpClientConnectionManagerBuilder builder = PoolingHttpClientConnectionManagerBuilder.create();
        if (ssl) {
            builder.setSSLSocketFactory(createSocketFactory());
        }
        if (readTimeout != null) {
            SocketConfig socketConfig = SocketConfig.custom()
                                                    .setSoTimeout((int) readTimeout.toMillis(), TimeUnit.MILLISECONDS)
                                                    .build();
            builder.setDefaultSocketConfig(socketConfig);
        }
        return builder.build();
    }

    private SSLConnectionSocketFactory createSocketFactory()
        throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException {
        SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustSelfSignedStrategy())
                                                       .build();
        return SSLConnectionSocketFactoryBuilder.create().setSslContext(sslContext)
                                                .setTlsVersions(TLS.V_1_3, TLS.V_1_2).build();
    }

    @Override
//    protected HttpContext createHttpContext(HttpMethod httpMethod, URI uri) {
//    protected HttpClientContext createHttpContext(HttpMethod httpMethod, URI uri) {
    protected HttpContext createHttpContext(HttpMethod httpMethod, URI uri) {
        HttpClientContext context = HttpClientContext.create();
        context.setRequestConfig(createRequestConfig());
        return context;
    }

    protected RequestConfig createRequestConfig() {
        RequestConfig.Builder builder = RequestConfig.custom();
        builder.setCookieSpec(this.cookieSpec);
        builder.setAuthenticationEnabled(false);
        builder.setRedirectsEnabled(this.enableRedirects);
        return builder.build();
    }
}
