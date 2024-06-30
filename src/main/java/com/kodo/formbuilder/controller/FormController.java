package com.kodo.formbuilder.controller;

import com.kodo.formbuilder.model.Form;
import com.kodo.formbuilder.service.FormService;
import com.kodo.formbuilder.service.ValidationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/forms")
@Tag(name = "Form API", description = "Endpoints for managing forms")
public class FormController {

    @Autowired
    private FormService formService;

    @Autowired
    private ValidationService validationService;

    @Operation(summary = "Create a new form")
    @ApiResponse(responseCode = "200", description = "Form created successfully")
    @ApiResponse(responseCode = "400", description = "Validation errors", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)))
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createForm(@RequestBody Form form) {
        List<String> validationErrors = validationService.validateForm(form);
        if (!validationErrors.isEmpty()) {
            return ResponseEntity.badRequest().body(validationErrors);
        }
        Form savedForm = formService.createForm(form);
        return ResponseEntity.ok(savedForm);
    }

    @Operation(summary = "Get a form by ID")
    @ApiResponse(responseCode = "200", description = "Form found")
    @ApiResponse(responseCode = "404", description = "Form not found")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Form> getFormById(@PathVariable Long id) {
        Optional<Form> form = formService.getFormById(id);
        return form.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Get all forms")
    @ApiResponse(responseCode = "200", description = "List of forms")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Form> getAllForms() {
        return formService.getAllForms();
    }
}
