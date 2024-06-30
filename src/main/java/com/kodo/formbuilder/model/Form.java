package com.kodo.formbuilder.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Form {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Form ID")
    private Long id;

    @Schema(description = "Title of the form", example = "Leave Application Form")
    private String title;

    @OneToMany(mappedBy = "form", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference // Breaks the infinite recursion in serialization
    @Schema(description = "List of fields in the form")
    private List<Field> fields;
}
