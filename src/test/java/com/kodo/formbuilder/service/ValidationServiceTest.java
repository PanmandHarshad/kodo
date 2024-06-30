package com.kodo.formbuilder.service;

import com.kodo.formbuilder.model.Field;
import com.kodo.formbuilder.model.Form;
import com.kodo.formbuilder.model.enums.FieldType;
import com.kodo.formbuilder.util.ErrorMessages;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValidationServiceTest {

    @InjectMocks
    private ValidationService validationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testValidateForm_Success() {
        // Arrange: Create a form with valid fields
        Form form = new Form();
        form.setTitle("Test Form");

        Field field = new Field();
        field.setLabel("Name");
        field.setType(FieldType.TEXT);
        field.setIsRequired(true);
        field.setValue("John Doe");

        form.setFields(Collections.singletonList(field));

        // Act: Call the validation service
        List<String> errors = validationService.validateForm(form);

        // Assert: Verify there are no validation errors
        assertEquals(0, errors.size());
    }

    @Test
    public void testValidateForm_EmptyTitle() {
        // Arrange: Create a form without a title
        Form form = new Form();

        // Act: Call the validation service
        List<String> errors = validationService.validateForm(form);

        // Assert: Verify there is a title required error
        assertEquals(1, errors.size());
        assertEquals(ErrorMessages.FORM_TITLE_REQUIRED, errors.get(0));
    }

    @Test
    public void testValidateForm_RequiredFieldMissing() {
        // Arrange: Create a form with a required field that has an empty value
        Form form = new Form();
        form.setTitle("Test Form");

        Field field = new Field();
        field.setLabel("Name");
        field.setType(FieldType.TEXT);
        field.setIsRequired(true);
        field.setValue("");

        form.setFields(Collections.singletonList(field));

        // Act: Call the validation service
        List<String> errors = validationService.validateForm(form);

        // Assert: Verify there is a required field value error
        assertEquals(1, errors.size());
        assertEquals(String.format(ErrorMessages.FIELD_VALUE_REQUIRED, field.getLabel()), errors.get(0));
    }
}
