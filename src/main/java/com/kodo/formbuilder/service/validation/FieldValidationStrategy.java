package com.kodo.formbuilder.service.validation;

import com.kodo.formbuilder.model.Field;

import java.util.List;

public interface FieldValidationStrategy {
    List<String> validate(Field field);
}
