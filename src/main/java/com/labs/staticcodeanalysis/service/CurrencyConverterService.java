package com.labs.staticcodeanalysis.service;

import com.labs.staticcodeanalysis.client.ApiCurrencyConverterClient;
import com.labs.staticcodeanalysis.client.CurrenciesRate;
import com.labs.staticcodeanalysis.domain.CurrencyApiResponse;
import com.labs.staticcodeanalysis.domain.CurrencyEnum;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CurrencyConverterService {
    
    private final ApiCurrencyConverterClient converterClient;

    public CurrencyConverterService(ApiCurrencyConverterClient converterClient) {
        this.converterClient = converterClient;
    }

    public CurrencyApiResponse convertFromTo(CurrencyEnum origin, CurrencyEnum target, BigDecimal valueOrigin){
        
        CurrenciesRate values = converterClient.getCurrenciesRateFor(origin);
        BigDecimal rateTarget = retrieveCurrencyRateTarget(values, target);
        BigDecimal valueTarget = calculateValueTarget(rateTarget, valueOrigin);
        
        return CurrencyApiResponse
                .builder()
                .originCurrency(origin)
                .originValue(valueOrigin)
                .targetCurrency(target)
                .targetValue(valueTarget)
                .build();
    }

    private BigDecimal calculateValueTarget(BigDecimal rateTarget, BigDecimal valueOrigin) {
        return rateTarget.multiply(valueOrigin);
    }

    private BigDecimal retrieveCurrencyRateTarget(CurrenciesRate values, CurrencyEnum target) {
        return values.getConversionRates().get(target);
    }

}
