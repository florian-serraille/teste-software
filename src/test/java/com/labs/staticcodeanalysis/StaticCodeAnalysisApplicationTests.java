package com.labs.staticcodeanalysis;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StaticCodeAnalysisApplicationTests {

    @Test
    void contextLoads() {

        Throwable throwable = Assertions.catchThrowable(() -> StaticCodeAnalysisApplication.main(new String[0]));
        Assertions.assertThat(throwable).isNull();
    }

}
