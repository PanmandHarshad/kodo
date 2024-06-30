package com.kodo.formbuilder.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.kodo.formbuilder.model.enums.FieldType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Field {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Field ID")
    private Long id;

    @Schema(description = "Label of the field", example = "Reason for Leave")
    private String label;

    @Schema(description = "Indicates if the field is required", example = "true")
    private Boolean isRequired;

    @Enumerated(EnumType.STRING)
    @Schema(description = "Type of the field", example = "TEXT")
    private FieldType type = FieldType.TEXT;

    @Schema(description = "Value of the field", example = "Health is not well")
    private String value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "form_id")
    @JsonBackReference // Breaks the infinite recursion in serialization
    private Form form;
}
