package com.labs.staticcodeanalysis.api;

import com.labs.staticcodeanalysis.domain.CurrencyApiResponse;
import com.labs.staticcodeanalysis.domain.CurrencyEnum;
import com.labs.staticcodeanalysis.service.CurrencyConverterService;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyConverterController {

    private final CurrencyConverterService currencyConverterService;

    public CurrencyConverterController(CurrencyConverterService currencyConverterService) {
        this.currencyConverterService = currencyConverterService;
    }

    @GetMapping("/{currencyOrigin}/{currencyTarget}")
    public ResponseEntity<CurrencyApiResponse> convertFromTo(@PathVariable CurrencyEnum currencyOrigin,
                                                             @PathVariable CurrencyEnum currencyTarget,
                                                             @RequestParam(name = "value", defaultValue = "1") @NumberFormat(style = NumberFormat.Style.CURRENCY) BigDecimal value){
        
        return ResponseEntity.ok(currencyConverterService.convertFromTo(currencyOrigin, currencyTarget, value));
    }
    

}
