package com.kodo.formbuilder.model.enums;

public enum FieldType {
    TEXT, NUMBER;

    public static boolean isValidType(String type) {
        for (FieldType fieldType : FieldType.values()) {
            if (fieldType.name().equalsIgnoreCase(type)) {
                return true;
            }
        }
        return false;
    }
}
