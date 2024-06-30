package com.kodo.formbuilder.controller;

import com.kodo.formbuilder.model.Form;
import com.kodo.formbuilder.service.FormService;
import com.kodo.formbuilder.service.ValidationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class FormControllerTest {

    // MockMvc provides support for testing Spring MVC controllers
    private MockMvc mockMvc;

    // Mocking the services that the controller depends on
    @Mock
    private FormService formService;

    @Mock
    private ValidationService validationService;

    // Injecting mocks into the controller
    @InjectMocks
    private FormController formController;

    @BeforeEach
    public void setUp() {
        // Initializing the mocks
        MockitoAnnotations.openMocks(this);

        // Setting up MockMvc for standalone usage
        mockMvc = MockMvcBuilders.standaloneSetup(formController).build();
    }

    @Test
    public void testCreateForm_Success() throws Exception {
        // Arrange: Create a form and mock the service methods
        Form form = new Form();
        form.setTitle("Test Form");

        when(validationService.validateForm(any(Form.class))).thenReturn(Collections.emptyList());
        when(formService.createForm(any(Form.class))).thenReturn(form);

        // Act & Assert: Perform POST request and verify response
        mockMvc.perform(post("/api/forms")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\": \"Test Form\"}"))
                .andExpect(status().isOk()) // Verify the status is OK (200)
                // Verify the title in the response JSON
                .andExpect(jsonPath("$.title").value("Test Form"));
    }

    @Test
    public void testCreateForm_ValidationError() throws Exception {
        // Arrange: Mock validation service to return an error
        when(validationService.validateForm(any(Form.class))).thenReturn(Collections.singletonList("Title is required"));

        // Act & Assert: Perform POST request and verify response
        mockMvc.perform(post("/api/forms")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\": \"\"}"))
                .andExpect(status().isBadRequest()) // Verify the status is Bad Request (400)
                // Verify the first error message in the response JSON array
                .andExpect(jsonPath("$[0]").value("Title is required"));
    }

    @Test
    public void testGetFormById_Success() throws Exception {
        // Arrange: Create a form and mock the service method
        Form form = new Form();
        form.setTitle("Test Form");

        when(formService.getFormById(1L)).thenReturn(Optional.of(form));

        // Act & Assert: Perform GET request and verify response
        mockMvc.perform(get("/api/forms/1"))
                .andExpect(status().isOk()) // Verify the status is OK (200)
                // Verify the title in the response JSON
                .andExpect(jsonPath("$.title").value("Test Form"));
    }

    @Test
    public void testGetFormById_NotFound() throws Exception {
        // Arrange: Mock the service method to return empty
        when(formService.getFormById(1L)).thenReturn(Optional.empty());

        // Act & Assert: Perform GET request and verify response
        mockMvc.perform(get("/api/forms/1"))
                .andExpect(status().isNotFound()); // Verify the status is Not Found (404)
    }

    @Test
    public void testGetAllForms() throws Exception {
        // Arrange: Create a form and mock the service method
        Form form = new Form();
        form.setTitle("Test Form");

        when(formService.getAllForms()).thenReturn(Collections.singletonList(form));

        // Act & Assert: Perform GET request and verify response
        mockMvc.perform(get("/api/forms"))
                .andExpect(status().isOk()) // Verify the status is OK (200)
                // Verify the title of the first form in the response JSON array
                .andExpect(jsonPath("$[0].title").value("Test Form"));
    }
}
