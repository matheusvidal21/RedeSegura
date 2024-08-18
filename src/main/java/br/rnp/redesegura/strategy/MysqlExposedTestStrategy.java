package br.rnp.redesegura.strategy;

import br.rnp.redesegura.dto.response.VulnerabilityTestResponse;
import br.rnp.redesegura.exception.FailedTestException;
import br.rnp.redesegura.models.Protocol;
import br.rnp.redesegura.models.Vulnerability;
import br.rnp.redesegura.models.enums.TestStatus;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

public class MysqlExposedTestStrategy implements VulnerabilityTestStrategy {
    @Override
    public VulnerabilityTestResponse test(Vulnerability vulnerability) {
        try {
            String ip = vulnerability.getService().getIp();
            Long port = vulnerability.getService().getPort();

            // Comando nc a ser executado
            String command = "wsl nc -w 2 " + ip + " " + port;

            // Executa o comando
            ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
            Process process = processBuilder.start();

            // Captura a saída do comando
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String output = reader.lines().collect(Collectors.joining("\n"));

            process.waitFor();

            var vulnerabilityTestResponse = createResponse(vulnerability);

            // Verifica a resposta
            if (output.contains("caching_sha2_password") || output.contains("mysql_native_password")) {
                vulnerabilityTestResponse.setTestStatus(TestStatus.VULNERABLE);
                vulnerabilityTestResponse.setTestResultMessage("O serviço MySQL está exposto e permite conexões não autenticadas. O cabeçalho do MySQL foi retornado, indicando vulnerabilidade.");
            } else if (output.isEmpty()) {
                vulnerabilityTestResponse.setTestStatus(TestStatus.NOT_VULNERABLE);
                vulnerabilityTestResponse.setTestResultMessage("O serviço MySQL não está exposto ou não respondeu a uma tentativa de conexão não autenticada.");
            } else {
                vulnerabilityTestResponse.setTestStatus(TestStatus.FAILED);
                vulnerabilityTestResponse.setTestResultMessage("Não foi possível determinar o status da vulnerabilidade com base na resposta recebida.");
            }

            return vulnerabilityTestResponse;
        } catch (Exception e) {
            throw new FailedTestException(String.format("Failed to test MySQL vulnerability: " + e.getMessage()), e);
        }
    }
}
