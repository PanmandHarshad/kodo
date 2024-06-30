package com.kodo.formbuilder.service;

import com.kodo.formbuilder.model.Form;
import com.kodo.formbuilder.repository.FormRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class FormServiceTest {

    @Mock
    private FormRepository formRepository;

    @InjectMocks
    private FormService formService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateForm() {
        // Arrange: Create a form and mock the repository method
        Form form = new Form();
        form.setTitle("Test Form");

        when(formRepository.save(any(Form.class))).thenReturn(form);

        // Act: Call the service method
        Form createdForm = formService.createForm(form);

        // Assert: Verify the result
        assertEquals("Test Form", createdForm.getTitle());
    }

    @Test
    public void testGetFormById_Success() {
        // Arrange: Create a form and mock the repository method
        Form form = new Form();
        form.setTitle("Test Form");

        when(formRepository.findById(1L)).thenReturn(Optional.of(form));

        // Act: Call the service method
        Optional<Form> foundForm = formService.getFormById(1L);

        // Assert: Verify the result
        assertTrue(foundForm.isPresent());
        assertEquals("Test Form", foundForm.get().getTitle());
    }

    @Test
    public void testGetFormById_NotFound() {
        // Arrange: Mock the repository method to return empty
        when(formRepository.findById(1L)).thenReturn(Optional.empty());

        // Act: Call the service method
        Optional<Form> foundForm = formService.getFormById(1L);

        // Assert: Verify the result
        assertFalse(foundForm.isPresent());
    }

    @Test
    public void testGetAllForms() {
        // Arrange: Create a form and mock the repository method
        Form form = new Form();
        form.setTitle("Test Form");

        when(formRepository.findAll()).thenReturn(Collections.singletonList(form));

        // Act & Assert: Call the service method and verify the result
        assertEquals(1, formService.getAllForms().size());
        assertEquals("Test Form", formService.getAllForms().get(0).getTitle());
    }
}
