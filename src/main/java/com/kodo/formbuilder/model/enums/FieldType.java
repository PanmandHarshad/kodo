package com.kodo.formbuilder.model.enums;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Enumeration representing types of fields.")
public enum FieldType {

    @Schema(description = "Text field type.")
    TEXT,

    @Schema(description = "Number field type.")
    NUMBER
}
