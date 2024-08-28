package br.rnp.redesegura.strategy;

import br.rnp.redesegura.dtos.response.VulnerabilityTestResponse;
import br.rnp.redesegura.exceptions.FailedTestException;
import br.rnp.redesegura.models.Vulnerability;
import br.rnp.redesegura.models.enums.TestStatus;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class MemcachedTestStrategy implements VulnerabilityTestStrategy {
    @Override
    public VulnerabilityTestResponse test(Vulnerability vulnerability) {
        try {
            String ip = vulnerability.getService().getIp();
            Long port = vulnerability.getService().getPort();

            // Comando para testar a exposição do Memcached
            String command = "docker exec tester nmap -sV -p " + port + " " + ip;

            // Executa o comando
            ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();

            // Captura a saída do comando
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String output = reader.lines().collect(Collectors.joining("\n"));

            var vulnerabilityTestResponse = createResponse(vulnerability);

            if (output.contains("open")) {
                vulnerabilityTestResponse.setTestStatus(TestStatus.VULNERABLE);
                vulnerabilityTestResponse.setTestResultMessage("O serviço Memcached está exposto e vulnerável. A porta 11211 está aberta e acessível.");
            } else {
                vulnerabilityTestResponse.setTestStatus(TestStatus.NOT_VULNERABLE);
                vulnerabilityTestResponse.setTestResultMessage("O serviço Memcached não está exposto. A porta 11211 está filtrada ou fechada.");
            }

            return vulnerabilityTestResponse;
        } catch (Exception e) {
            throw new FailedTestException(String.format("Failed to test Memcached vulnerability: " + e.getMessage()), e);
        }
    }
}
