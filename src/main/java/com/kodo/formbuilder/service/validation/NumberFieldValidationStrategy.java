package com.kodo.formbuilder.service.validation;

import com.kodo.formbuilder.model.Field;
import com.kodo.formbuilder.util.ErrorMessages;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;

public class NumberFieldValidationStrategy implements FieldValidationStrategy {

    @Value("${field.number.max-value}")
    private double numberMaxValue;

    @Value("${field.number.min-value}")
    private double numberMinValue;

    @Value("${field.number.integer-only}")
    private boolean numberIntegerOnly;

    @Value("${field.number.decimal-places}")
    private int numberDecimalPlaces;

    @Override
    public List<String> validate(Field field) {
        if (field.getLabel() == null || field.getLabel().isBlank()) {
            return List.of(ErrorMessages.FIELD_LABEL_REQUIRED);
        }

        List<String> errors = new ArrayList<>();
        if (field.getValue() != null) {
            try {
                double value = Double.parseDouble(field.getValue());
                if (value > numberMaxValue) {
                    errors.add(String.format(ErrorMessages.FIELD_EXCEEDS_MAX_VALUE, field.getLabel()));
                }
                if (value < numberMinValue) {
                    errors.add(String.format(ErrorMessages.FIELD_BELOW_MIN_VALUE, field.getLabel()));
                }
                if (numberIntegerOnly && value % 1 != 0) {
                    errors.add(String.format(ErrorMessages.FIELD_MUST_BE_INTEGER, field.getLabel()));
                }
                if (value % 1 != 0) {
                    String[] splitValue = String.valueOf(value).split("\\.");
                    if (splitValue.length > 1 && splitValue[1].length() > numberDecimalPlaces) {
                        errors.add(String.format(ErrorMessages.FIELD_EXCEEDS_DECIMAL_PLACES, field.getLabel()));
                    }
                }
            } catch (NumberFormatException e) {
                errors.add(String.format(ErrorMessages.FIELD_NOT_VALID_NUMBER, field.getLabel()));
            }
        }
        return errors;
    }
}
