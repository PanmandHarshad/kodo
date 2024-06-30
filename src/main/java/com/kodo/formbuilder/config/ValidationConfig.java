package com.kodo.formbuilder.config;

import com.kodo.formbuilder.service.validation.NumberFieldValidationStrategy;
import com.kodo.formbuilder.service.validation.TextFieldValidationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidationConfig {

    @Bean
    public TextFieldValidationStrategy textFieldValidationStrategy() {
        return new TextFieldValidationStrategy();
    }

    @Bean
    public NumberFieldValidationStrategy numberFieldValidationStrategy() {
        return new NumberFieldValidationStrategy();
    }
}
