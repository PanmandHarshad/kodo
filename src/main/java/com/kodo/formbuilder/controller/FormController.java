package com.kodo.formbuilder.controller;

import com.kodo.formbuilder.model.Form;
import com.kodo.formbuilder.service.FormService;
import com.kodo.formbuilder.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/forms")
public class FormController {

    @Autowired
    private FormService formService;

    @Autowired
    private ValidationService validationService;

    @PostMapping
    public ResponseEntity<?> createForm(@RequestBody Form form) {
        List<String> validationErrors = validationService.validateForm(form);
        if (!validationErrors.isEmpty()) {
            return ResponseEntity.badRequest().body(validationErrors);
        }
        Form savedForm = formService.createForm(form);
        return ResponseEntity.ok(savedForm);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFormById(@PathVariable Long id) {
        Optional<Form> form = formService.getFormById(id);
        return form.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Form> getAllForms() {
        return formService.getAllForms();
    }
}
