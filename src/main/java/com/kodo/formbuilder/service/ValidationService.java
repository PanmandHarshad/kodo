package com.kodo.formbuilder.service;

import com.kodo.formbuilder.model.Field;
import com.kodo.formbuilder.model.Form;
import com.kodo.formbuilder.model.enums.FieldType;
import com.kodo.formbuilder.service.validation.FieldValidationStrategy;
import com.kodo.formbuilder.service.validation.NumberFieldValidationStrategy;
import com.kodo.formbuilder.service.validation.TextFieldValidationStrategy;
import com.kodo.formbuilder.util.ErrorMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service class for validating forms and fields.
 */
@Service
public class ValidationService {

    private final Map<FieldType, FieldValidationStrategy> validationStrategies = new HashMap<>();

    @Autowired
    public ValidationService(
            TextFieldValidationStrategy textFieldValidationStrategy,
            NumberFieldValidationStrategy numberFieldValidationStrategy) {
        validationStrategies.put(FieldType.TEXT, textFieldValidationStrategy);
        validationStrategies.put(FieldType.NUMBER, numberFieldValidationStrategy);
    }

    /**
     * Validates a form.
     *
     * @param form the form to be validated.
     * @return a list of validation error messages.
     */
    public List<String> validateForm(Form form) {
        if (form.getTitle() == null || form.getTitle().isBlank()) {
            return List.of(ErrorMessages.FORM_TITLE_REQUIRED);
        }

        List<String> errors = new ArrayList<>();

        for (Field field : form.getFields()) {
            if (field.getIsRequired()
                    && (field.getType() == null || field.getType().toString().isBlank())
                    || (field.getValue() == null || field.getValue().isEmpty())) {
                errors.add(String.format(ErrorMessages.FIELD_VALUE_REQUIRED, field.getLabel()));
            } else {
                FieldValidationStrategy validationStrategy = validationStrategies.get(field.getType());
                if (validationStrategy != null) {
                    errors.addAll(validationStrategy.validate(field));
                }
            }
        }
        return errors;
    }
}
