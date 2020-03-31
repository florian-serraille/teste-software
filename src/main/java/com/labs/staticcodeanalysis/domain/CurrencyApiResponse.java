package com.labs.staticcodeanalysis.domain;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
public class CurrencyApiResponse {

    @Getter
    private CurrencyEnum originCurrency;
    
    private String currencyOriginDescription;
    
    @Getter
    private BigDecimal originValue;
    
    @Getter
    private CurrencyEnum targetCurrency;
    
    private String currencyTargetDescription;
    
    @Getter
    private BigDecimal targetValue;

    public String getCurrencyOriginDescription() {
        return buildCurrencyDescription(this.originCurrency);
    }

    public String getCurrencyTargetDescription() {
        return buildCurrencyDescription(this.targetCurrency);
    }
    
    private String buildCurrencyDescription(CurrencyEnum currencyEnum){
        return currencyEnum.getCurency() + " - " + currencyEnum.getCounty();
    }
}
