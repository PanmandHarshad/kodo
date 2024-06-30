package com.kodo.formbuilder.service.validation;

import com.kodo.formbuilder.model.Field;

import java.util.List;

/**
 * Interface for field validation strategy.
 */
public interface FieldValidationStrategy {

    /**
     * Validates a given field.
     *
     * @param field the field to be validated.
     * @return List of validation error messages.
     */
    List<String> validate(Field field);
}
