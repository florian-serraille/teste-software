package com.labs.staticcodeanalysis.service;

import com.labs.staticcodeanalysis.client.ApiCurrencyConverterClient;
import com.labs.staticcodeanalysis.client.CurrenciesRate;
import com.labs.staticcodeanalysis.domain.CurrencyApiResponse;
import com.labs.staticcodeanalysis.domain.CurrencyEnum;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.concurrent.ExecutionException;

@Service
public class Currency_converter_service {
    
    public ApiCurrencyConverterClient converterClient;

    public Currency_converter_service(ApiCurrencyConverterClient converterClient) {
        this.converterClient = converterClient;
    }

    public CurrencyApiResponse convertFromTo(CurrencyEnum origin, CurrencyEnum target, BigDecimal valueOrigin){

        CurrenciesRate values;
        try {   
            values = converterClient.getCurrenciesRateFor(origin);
        } catch (Exception e){
            values = new CurrenciesRate();
        }
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
