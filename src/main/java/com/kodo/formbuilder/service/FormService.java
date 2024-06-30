package com.kodo.formbuilder.service;

import com.kodo.formbuilder.model.Form;
import com.kodo.formbuilder.repository.FormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing forms.
 */
@Service
public class FormService {

    @Autowired
    private FormRepository formRepository;

    /**
     * Creates a new form.
     *
     * @param form the form to be created.
     * @return the saved form.
     */
    public Form createForm(Form form) {
        return formRepository.save(form);
    }

    /**
     * Retrieves a form by its ID.
     *
     * @param id the ID of the form.
     * @return an Optional containing the form if found, or empty if not found.
     */
    public Optional<Form> getFormById(Long id) {
        return formRepository.findById(id);
    }

    /**
     * Retrieves all forms.
     *
     * @return a list of all forms.
     */
    public List<Form> getAllForms() {
        return formRepository.findAll();
    }
}
