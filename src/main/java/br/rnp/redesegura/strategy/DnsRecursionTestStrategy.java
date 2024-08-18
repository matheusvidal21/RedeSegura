package br.rnp.redesegura.strategy;

import br.rnp.redesegura.dto.response.VulnerabilityTestResponse;
import br.rnp.redesegura.exception.FailedTestException;
import br.rnp.redesegura.models.Vulnerability;
import br.rnp.redesegura.models.enums.TestStatus;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

public class DnsRecursionTestStrategy implements VulnerabilityTestStrategy {

    @Override
    public VulnerabilityTestResponse test(Vulnerability vulnerability) {
        try {
            String ip = vulnerability.getService().getIp();
            // Comando dig a ser executado
            String command = "wsl dig google.com A @" + ip;

            // Executa o comando
            ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
            Process process = processBuilder.start();

            // Captura a saída do comando
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            boolean isVulnerable = false;

            // Analisa a saída
            while ((line = reader.readLine()) != null) {
                if (line.contains("flags:") && line.contains("ra")) {
                    isVulnerable = true;
                    break;
                }
            }

            process.waitFor();

            VulnerabilityTestResponse vulnerabilityTestResponse = VulnerabilityTestResponse.builder()
                    .vulnerabilityId(vulnerability.getId())
                    .vulnerabilityTitle(vulnerability.getTitle())
                    .service(vulnerability.getService().getName())
                    .ip(ip)
                    .port(vulnerability.getService().getPort())
                    .protocols(vulnerability.getService().getProtocols().stream().map(protocol -> protocol.getName()).collect(Collectors.toSet()))
                    .testedAt(LocalDateTime.now().toString())
                    .build();

            if (isVulnerable){
                vulnerabilityTestResponse.setTestStatus(TestStatus.VULNERABLE);
                vulnerabilityTestResponse.setTestResultMessage("O serviço DNS está vulnerável à recursão, permitindo possíveis ataques de DDoS. A configuração atual permite que solicitações de recursão sejam processadas, o que pode ser explorado por atacantes.");
            } else {
                vulnerabilityTestResponse.setTestStatus(TestStatus.NOT_VULNERABLE);
                vulnerabilityTestResponse.setTestResultMessage("O serviço DNS não está vulnerável à recursão. A configuração atual bloqueia solicitações de recursão, mitigando a possibilidade de ataques de DDoS.");
            }

            return vulnerabilityTestResponse;
        } catch (Exception e) {
            throw new FailedTestException("Failed to test vulnerability", e);
        }
    }
}
