package br.rnp.redesegura.models.enums;

import java.util.Arrays;

public enum ServiceStatus {

    UP("Up"),
    DOWN("Down"),
    HEALTHY("Healthy"),
    DEGRADED("Degraded"),
    UNKNOWN("Unknown");

    private String value;

    ServiceStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static ServiceStatus fromString(String status) {
        return Arrays.stream(ServiceStatus.values())
                .filter(serviceStatus -> serviceStatus.getValue().equalsIgnoreCase(status))
                .findFirst()
                .orElse(ServiceStatus.UNKNOWN);
    }

}
