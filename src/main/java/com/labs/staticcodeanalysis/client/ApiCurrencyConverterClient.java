package com.labs.staticcodeanalysis.client;

import com.labs.staticcodeanalysis.domain.CurrencyEnum;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
@ConfigurationProperties(prefix = "client.api")
public class ApiCurrencyConverterClient {

    @Setter
    private String scheme;
    @Setter
    private String host;
    @Setter
    private String version;
    @Setter
    private String key;
    @Setter
    private String endpoint;


    public CurrenciesRate getCurrenciesRateFor(CurrencyEnum origin) {

        URI uri = buildURI(origin);
        return doRequest(uri);
    }

    private CurrenciesRate doRequest(URI uri) {
        RestTemplate restTemplate = new RestTemplateBuilder().build();
        ResponseEntity<CurrenciesRate> entity = restTemplate.getForEntity(uri, CurrenciesRate.class);
        return entity.getBody();
    }

    private URI buildURI(CurrencyEnum origin) {
        return UriComponentsBuilder
                .newInstance()
                .scheme(scheme)
                .host(host)
                .pathSegment(version, key, endpoint, origin.name())
                .build()
                .toUri();
    }
}
