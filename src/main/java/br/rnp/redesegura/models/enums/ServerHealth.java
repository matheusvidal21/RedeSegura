package br.rnp.redesegura.models.enums;

import java.util.Arrays;

public enum ServerHealth {

    OPERATIONAL("Operational"),
    PARTIALLY_OPERATIONAL("Partially Operational"),
    DEGRADED("Degraded"),
    WARNING("Warning"),
    MAINTENANCE("Maintenance"),
    UNKNOWN("Unknown");

    private String value;

    ServerHealth(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static ServerHealth fromValue(String value) {
        return Arrays.stream(ServerHealth.values())
                .filter(sh -> sh.getValue().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid value: " + value));
    }

}
