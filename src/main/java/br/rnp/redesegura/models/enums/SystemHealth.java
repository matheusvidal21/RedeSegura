package br.rnp.redesegura.models.enums;

import java.util.Arrays;

public enum SystemHealth {

    OPERATIONAL("Operational"),
    PARTIALLY_OPERATIONAL("Partially Operational"),
    DEGRADED("Degraded"),
    WARNING("Warning"),
    MAINTENANCE("Maintenance");

    private String value;

    SystemHealth(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static SystemHealth fromValue(String value) {
        return Arrays.stream(SystemHealth.values())
                .filter(sh -> sh.getValue().equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid value: " + value));
    }

}
