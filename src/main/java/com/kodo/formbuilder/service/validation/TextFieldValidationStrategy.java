package com.kodo.formbuilder.service.validation;

import com.kodo.formbuilder.model.Field;
import com.kodo.formbuilder.util.ErrorMessages;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class TextFieldValidationStrategy implements FieldValidationStrategy {

    @Value("${field.text.max-length}")
    private int textMaxLength;

    @Value("${field.text.min-length}")
    private int textMinLength;

    @Value("${field.text.pattern}")
    private String textPattern;

    @Override
    public List<String> validate(Field field) {
        if (field.getLabel() == null || field.getLabel().isBlank()) {
            return List.of(ErrorMessages.FIELD_LABEL_REQUIRED);
        }

        List<String> errors = new ArrayList<>();
        if (field.getValue() != null) {
            if (field.getValue().length() > textMaxLength) {
                errors.add(String.format(ErrorMessages.FIELD_EXCEEDS_MAX_LENGTH, field.getLabel()));
            }
            if (field.getValue().length() < textMinLength) {
                errors.add(String.format(ErrorMessages.FIELD_DOES_NOT_MEET_MIN_LENGTH, field.getLabel()));
            }
            Pattern pattern = Pattern.compile(textPattern);
            if (!pattern.matcher(field.getValue()).matches()) {
                errors.add(String.format(ErrorMessages.FIELD_DOES_NOT_MATCH_PATTERN, field.getLabel()));
            }
        }
        return errors;
    }

}
