package com.kodo.formbuilder.config;

import com.kodo.formbuilder.service.validation.NumberFieldValidationStrategy;
import com.kodo.formbuilder.service.validation.TextFieldValidationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for validation strategies.
 */
@Configuration
public class ValidationConfig {

    /**
     * Creates a bean for text field validation strategy.
     *
     * @return an instance of TextFieldValidationStrategy.
     */
    @Bean
    public TextFieldValidationStrategy textFieldValidationStrategy() {
        return new TextFieldValidationStrategy();
    }

    /**
     * Creates a bean for number field validation strategy.
     *
     * @return an instance of NumberFieldValidationStrategy.
     */
    @Bean
    public NumberFieldValidationStrategy numberFieldValidationStrategy() {
        return new NumberFieldValidationStrategy();
    }
}
