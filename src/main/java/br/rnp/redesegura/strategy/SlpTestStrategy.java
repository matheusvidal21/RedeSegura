package br.rnp.redesegura.strategy;

import br.rnp.redesegura.dtos.response.VulnerabilityTestResponse;
import br.rnp.redesegura.exceptions.FailedTestException;
import br.rnp.redesegura.models.Vulnerability;
import br.rnp.redesegura.models.enums.TestStatus;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class SlpTestStrategy implements VulnerabilityTestStrategy {
    @Override
    public VulnerabilityTestResponse test(Vulnerability vulnerability) {
        try {
            String ip = vulnerability.getService().getIp();
            Long port = vulnerability.getService().getPort();

            // Comando para testar a exposição do SLP
            String command = "docker exec tester nmap -sS -p " + port + " " + ip;

            // Executa o comando
            ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
            processBuilder.redirectErrorStream(true); // Captura também o stderr
            Process process = processBuilder.start();

            // Captura a saída do comando
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String output = reader.lines().collect(Collectors.joining("\n"));

            process.waitFor(); // Aguarda o término do processo

            var vulnerabilityTestResponse = createResponse(vulnerability);

            if (output.contains("open")) {
                vulnerabilityTestResponse.setTestStatus(TestStatus.VULNERABLE);
                vulnerabilityTestResponse.setTestResultMessage("O serviço SLP está exposto e vulnerável. A porta " + port + " está aberta e acessível.");
            } else if (output.contains("filtered") || output.contains("closed")) {
                vulnerabilityTestResponse.setTestStatus(TestStatus.NOT_VULNERABLE);
                vulnerabilityTestResponse.setTestResultMessage("O serviço SLP não está exposto. A porta " + port + " está filtrada.");
            } else {
                vulnerabilityTestResponse.setTestStatus(TestStatus.FAILED);
                vulnerabilityTestResponse.setTestResultMessage("Não foi possível determinar o status da porta " + port + " para o serviço SLP.");
            }

            return vulnerabilityTestResponse;
        } catch (Exception e) {
            throw new FailedTestException(String.format("Failed to test SLP vulnerability: " + e.getMessage()), e);
        }
    }
}
