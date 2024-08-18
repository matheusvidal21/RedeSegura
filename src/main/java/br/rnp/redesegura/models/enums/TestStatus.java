package br.rnp.redesegura.models.enums;

import java.util.Arrays;

public enum TestStatus {

    NOT_VULNERABLE("Not Vulnerable"),     // Teste concluído, vulnerabilidade não encontrada
    VULNERABLE("Vulnerable"),             // Teste concluído, vulnerabilidade confirmada
    ERROR("Error"),                       // Falha na execução do teste
    FAILED("Failed");                     // Erro inesperado durante o teste

    private String status;

    TestStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public static TestStatus fromString(String status) {
        return Arrays.stream(values())
                .filter(testStatus -> testStatus.getStatus().equalsIgnoreCase(status))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid status: " + status));
    }

}
