package com.labs.staticcodeanalysis.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.labs.staticcodeanalysis.domain.CurrencyEnum;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.MockRestServiceServer;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@ExtendWith(SpringExtension.class)
@RestClientTest(ApiCurrencyConverterClient.class)
class ApiCurrencyConverterClientTest {

    @Autowired
    private ApiCurrencyConverterClient client;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getCurrenciesRateFor() throws JsonProcessingException {
        
        // Given
        CurrencyEnum currency = CurrencyEnum.BRL;
        
        String body = objectMapper.writeValueAsString(new CurrenciesRate());

        MockRestServiceServer server = MockRestServiceServer.bindTo(new RestTemplateBuilder().build()).build();
        server.expect(requestTo("https://prime.exchangerate-api.com/v5/2f95666c60dbf5a9220fe395/latest/" + currency.name()))
                .andRespond(withSuccess(body, MediaType.APPLICATION_JSON));
        
        // When
        CurrenciesRate currenciesRateFor = client.getCurrenciesRateFor(currency);

        // Then
        Assertions.assertThat(currenciesRateFor).isNotNull();
        
        
    }
}