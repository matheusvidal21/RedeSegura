package br.rnp.redesegura.models.enums;

import java.util.Arrays;

public enum Severity {

    LOW("Low"),
    MEDIUM("Medium"),
    HIGH("High"),
    CRITICAL("Critical");

    private String value;

    Severity(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Severity fromValue(String value) {
        return Arrays.stream(Severity.values())
                .filter(severity -> severity.getValue().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid severity value: " + value));
    }

}
