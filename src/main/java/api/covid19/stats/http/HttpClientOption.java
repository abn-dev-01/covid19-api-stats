package api.covid19.stats.http;

public enum HttpClientOption {

    /**
     * Enable cookies.
     */
    ENABLE_COOKIES,

    /**
     * Enable redirects.
     */
    ENABLE_REDIRECTS,

    /**
     * Use a {@link org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory} with {@link org.apache.hc.client5.http.ssl.TrustSelfSignedStrategy}.
     */
    SSL
}
