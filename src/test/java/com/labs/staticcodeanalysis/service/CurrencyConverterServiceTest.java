package com.labs.staticcodeanalysis.service;

import com.labs.staticcodeanalysis.client.ApiCurrencyConverterClient;
import com.labs.staticcodeanalysis.client.CurrenciesRate;
import com.labs.staticcodeanalysis.domain.CurrencyApiResponse;
import com.labs.staticcodeanalysis.domain.CurrencyEnum;
import org.assertj.core.api.Assertions;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.HashMap;

class CurrencyConverterServiceTest {

    @Test
    void convertFromTo() {

        // Given
        ApiCurrencyConverterClient clientMock = Mockito.mock(ApiCurrencyConverterClient.class);
        CurrencyConverterService converterService = new CurrencyConverterService(clientMock);

        CurrencyEnum originCurrency = CurrencyEnum.BRL;
        CurrencyEnum targetCurrency = CurrencyEnum.USD;

        CurrenciesRate rates = buildRateFor(targetCurrency);
        Mockito.when(clientMock.getCurrenciesRateFor(CurrencyEnum.BRL))
                .thenReturn(rates);

        // When
        CurrencyApiResponse response = converterService.convertFromTo(originCurrency, targetCurrency, BigDecimal.valueOf(1));

        // Then
        Assertions.assertThat(response)
                .isNotNull()
                .extracting(
                        "originCurrency",
                        "currencyOriginDescription",
                        "originValue",
                        "targetCurrency",
                        "currencyTargetDescription",
                        "targetValue")
                .contains(originCurrency,
                        targetCurrency)
                .doesNotContainNull();
    }

    private CurrenciesRate buildRateFor(CurrencyEnum targetCurrency) {

        HashMap<CurrencyEnum, BigDecimal> map = new HashMap<>();
        map.put(targetCurrency, BigDecimal.valueOf(5.0));

        CurrenciesRate rate = new CurrenciesRate();
        rate.setConversionRates(map);
        return rate;
    }
}