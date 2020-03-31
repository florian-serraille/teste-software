package com.labs.staticcodeanalysis.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.labs.staticcodeanalysis.domain.CurrencyEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Map;

@Getter
@Setter
public class CurrenciesRate {
    
    @JsonProperty("conversion_rates")
    Map<CurrencyEnum, BigDecimal> conversionRates;
    
}
