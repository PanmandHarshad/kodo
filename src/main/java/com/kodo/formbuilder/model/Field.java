package com.kodo.formbuilder.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.kodo.formbuilder.model.enums.FieldType;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Field {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String label;
    private Boolean isRequired;

    @Enumerated(EnumType.STRING)
    private FieldType type = FieldType.TEXT;

    private String value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "form_id")
    @JsonBackReference // Add this to break the infinite recursion
    private Form form;
}

