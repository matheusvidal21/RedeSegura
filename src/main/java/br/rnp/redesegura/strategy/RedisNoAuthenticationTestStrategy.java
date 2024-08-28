package br.rnp.redesegura.strategy;

import br.rnp.redesegura.dtos.response.VulnerabilityTestResponse;
import br.rnp.redesegura.exceptions.FailedTestException;
import br.rnp.redesegura.models.Vulnerability;
import br.rnp.redesegura.models.enums.TestStatus;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class RedisNoAuthenticationTestStrategy implements VulnerabilityTestStrategy {
    @Override
    public VulnerabilityTestResponse test(Vulnerability vulnerability) {
        try {
            String ip = vulnerability.getService().getIp();
            Long port = vulnerability.getService().getPort();

            // Comando redis-cli a ser executado para verificar autenticação
            String command = "docker exec tester redis-cli -h " + ip + " -p " + port + " PING";

            // Executa o comando
            ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
            processBuilder.redirectErrorStream(true); // Redireciona stderr para stdout
            Process process = processBuilder.start();

            // Captura a saída do comando (stdout e stderr juntos)
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String output = reader.lines().collect(Collectors.joining("\n"));

            process.waitFor();

            var vulnerabilityTestResponse = createResponse(vulnerability);

            // Verifica a resposta
            if (output.contains("PONG")) {
                vulnerabilityTestResponse.setTestStatus(TestStatus.VULNERABLE);
                vulnerabilityTestResponse.setTestResultMessage("O serviço Redis está exposto e permite conexões sem autenticação. O comando PING retornou PONG, indicando vulnerabilidade.");
            } else if (output.contains("NOAUTH Authentication required")) {
                vulnerabilityTestResponse.setTestStatus(TestStatus.NOT_VULNERABLE);
                vulnerabilityTestResponse.setTestResultMessage("O serviço Redis requer autenticação. O comando PING foi bloqueado, indicando que o serviço está seguro.");
            } else if (output.contains("Connection refused") || output.contains("Connection reset by peer")) {
                vulnerabilityTestResponse.setTestStatus(TestStatus.NOT_VULNERABLE);
                vulnerabilityTestResponse.setTestResultMessage("O serviço Redis não está acessível. Conexão recusada, indicando que o serviço está seguro.");
            } else {
                vulnerabilityTestResponse.setTestStatus(TestStatus.FAILED);
                vulnerabilityTestResponse.setTestResultMessage("Não foi possível determinar o status da vulnerabilidade com base na resposta recebida.");
            }

            return vulnerabilityTestResponse;
        } catch (Exception e) {
            throw new FailedTestException("Failed to test Redis vulnerability: " + e.getMessage(), e);
        }
    }
}
