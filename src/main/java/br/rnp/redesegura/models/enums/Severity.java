package br.rnp.redesegura.models.enums;

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
        for (Severity severity : Severity.values()) {
            if (severity.getValue().equals(value)) {
                return severity;
            }
        }
        return null;
    }

}
