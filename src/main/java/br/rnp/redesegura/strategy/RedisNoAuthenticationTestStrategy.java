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

public class RedisNoAuthenticationTestStrategy implements VulnerabilityTestStrategy {
    @Override
    public VulnerabilityTestResponse test(Vulnerability vulnerability) {
        try {
            String ip = vulnerability.getService().getIp();
            Long port = vulnerability.getService().getPort();

            // Comando nc com opção -v para modo verbose
            String command = "wsl nc -v -w 3 " + ip + " " + port;

            // Executa o comando
            ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
            processBuilder.redirectErrorStream(true); // Redireciona stderr para stdout
            Process process = processBuilder.start();

            // Captura a saída do comando (stdout e stderr juntos)
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String output = reader.lines().collect(Collectors.joining("\n"));

            process.waitFor();

            VulnerabilityTestResponse vulnerabilityTestResponse = VulnerabilityTestResponse.builder()
                    .vulnerabilityId(vulnerability.getId())
                    .vulnerabilityTitle(vulnerability.getTitle())
                    .service(vulnerability.getService().getName())
                    .ip(ip)
                    .port(port)
                    .protocols(vulnerability.getService().getProtocols().stream().map(Protocol::getName).collect(Collectors.toSet()))
                    .testedAt(LocalDateTime.now().toString())
                    .build();

            // Verifica a resposta
            if (output.contains("succeeded")) {
                vulnerabilityTestResponse.setTestStatus(TestStatus.VULNERABLE);
                vulnerabilityTestResponse.setTestResultMessage("O serviço Redis está exposto e permite conexões sem autenticação. Conexão estabelecida com sucesso, indicando vulnerabilidade.");
            } else if (output.contains("Connection refused")) {
                vulnerabilityTestResponse.setTestStatus(TestStatus.NOT_VULNERABLE);
                vulnerabilityTestResponse.setTestResultMessage("O serviço Redis não está exposto. Conexão recusada, indicando que o serviço está seguro.");
            } else {
                vulnerabilityTestResponse.setTestStatus(TestStatus.FAILED);
                vulnerabilityTestResponse.setTestResultMessage("Não foi possível determinar o status da vulnerabilidade com base na resposta recebida.");
            }

            return vulnerabilityTestResponse;
        } catch (Exception e) {
            throw new FailedTestException(String.format("Failed to test Redis vulnerability: " + e.getMessage()), e);
        }
    }
}
