package br.rnp.redesegura.strategy;

import br.rnp.redesegura.dtos.response.VulnerabilityTestResponse;
import br.rnp.redesegura.exceptions.FailedTestException;
import br.rnp.redesegura.models.Vulnerability;
import br.rnp.redesegura.models.enums.TestStatus;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class NetbiosExposureTestStrategy implements VulnerabilityTestStrategy {
    @Override
    public VulnerabilityTestResponse test(Vulnerability vulnerability) {
        try {
            String ip = vulnerability.getService().getIp();

            // Comando nmblookup a ser executado
            String command = "docker exec tester nmblookup -A " + ip;

            // Executa o comando
            ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
            Process process = processBuilder.start();

            // Captura a saída do comando
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            boolean isVulnerable = true;

            // Analisa a saída
            while ((line = reader.readLine()) != null) {
                if (line.contains("No reply from")) {
                    isVulnerable = false;
                    break;
                }
            }

            process.waitFor();

            var vulnerabilityTestResponse = createResponse(vulnerability);

            if (isVulnerable){
                vulnerabilityTestResponse.setTestStatus(TestStatus.VULNERABLE);
                vulnerabilityTestResponse.setTestResultMessage("O serviço NetBIOS está vulnerável à exposição de informações sensíveis. O comando nmblookup retornou dados, indicando que o serviço está acessível e exposto.");
            } else {
                vulnerabilityTestResponse.setTestStatus(TestStatus.NOT_VULNERABLE);
                vulnerabilityTestResponse.setTestResultMessage("O serviço NetBIOS não está vulnerável à exposição de informações. O comando nmblookup não retornou dados, indicando que o serviço está seguro.");
            }

            return vulnerabilityTestResponse;
        } catch (Exception e) {
            throw new FailedTestException(String.format("Failed to test NetBIOS Exposure vulnerability: " + e.getMessage()), e);
        }
    }

}
