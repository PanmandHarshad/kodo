package com.kodo.formbuilder.model;

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
    private FieldType type = FieldType.TEXT; // Provide a default value

    private String value;

    @ManyToOne
    @JoinColumn(name = "form_id")
    private Form form;

}
