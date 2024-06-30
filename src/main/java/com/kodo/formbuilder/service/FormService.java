package com.kodo.formbuilder.service;

import com.kodo.formbuilder.model.Form;
import com.kodo.formbuilder.repository.FormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FormService {

    @Autowired
    private FormRepository formRepository;

    public Form createForm(Form form) {
        return formRepository.save(form);
    }

    public Optional<Form> getFormById(Long id) {
        return formRepository.findById(id);
    }

    public List<Form> getAllForms() {
        return formRepository.findAll();
    }
}
