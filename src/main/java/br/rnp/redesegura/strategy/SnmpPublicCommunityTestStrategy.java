package br.rnp.redesegura.strategy;

import br.rnp.redesegura.dtos.response.VulnerabilityTestResponse;
import br.rnp.redesegura.exceptions.FailedTestException;
import br.rnp.redesegura.models.Vulnerability;
import br.rnp.redesegura.models.enums.TestStatus;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SnmpPublicCommunityTestStrategy implements VulnerabilityTestStrategy {
    @Override
    public VulnerabilityTestResponse test(Vulnerability vulnerability) {
        try {
            String ip = vulnerability.getService().getIp();
            // Comando snmpwalk a ser executado
            String command = "docker exec tester snmpwalk -c public -v1 " + ip;

            // Executa o comando
            ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
            Process process = processBuilder.start();

            // Captura a saída padrão
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
            // Captura a saída de erro
            BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            // Captura a saída do comando
            StringBuilder output = new StringBuilder();
            String line;
            boolean isVulnerable = true;

            // Analisa a saída de erro
            while ((line = stdError.readLine()) != null) {
                if (line.contains("No Response")) {
                    isVulnerable = false;
                    break;
                }
            }

            // Analisa a saída padrão
            while ((line = stdInput.readLine()) != null) {
                output.append(line).append("\n");
                if (line.contains("iso")) { // Verifica se há dados SNMP retornados
                    isVulnerable = true;
                }
            }

            process.waitFor();

            var vulnerabilityTestResponse = createResponse(vulnerability);

            if (isVulnerable) {
                vulnerabilityTestResponse.setTestStatus(TestStatus.VULNERABLE);
                vulnerabilityTestResponse.setTestResultMessage("O serviço SNMP está vulnerável. A comunidade 'public' está exposta e retornou dados, o que pode ser explorado para manipulação ou ataques de DDoS.");
            } else {
                vulnerabilityTestResponse.setTestStatus(TestStatus.NOT_VULNERABLE);
                vulnerabilityTestResponse.setTestResultMessage("O serviço SNMP não está vulnerável. Não houve resposta ao comando, indicando que a comunidade 'public' não está exposta.");
            }

            return vulnerabilityTestResponse;
        } catch (Exception e) {
            throw new FailedTestException(String.format("Failed to test vulnerability: " + e.getMessage()), e);
        }
    }

}
