package com.labs.staticcodeanalysis.api;

import com.labs.staticcodeanalysis.service.CurrencyConverterService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = CurrencyConverterController.class)
class CurrencyConverterControllerTest {

    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private CurrencyConverterService currencyConverterService;
    
    @Test
    void convertFromTo() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/BRL/USD"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        
    }
}