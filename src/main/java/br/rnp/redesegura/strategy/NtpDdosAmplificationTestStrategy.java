package br.rnp.redesegura.strategy;

import br.rnp.redesegura.dtos.response.VulnerabilityTestResponse;
import br.rnp.redesegura.exceptions.FailedTestException;
import br.rnp.redesegura.models.Vulnerability;
import br.rnp.redesegura.models.enums.TestStatus;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class NtpDdosAmplificationTestStrategy implements VulnerabilityTestStrategy {
    @Override
    public VulnerabilityTestResponse test(Vulnerability vulnerability) {
        try {
            String ip = vulnerability.getService().getIp();
            Long port = vulnerability.getService().getPort();
            // Comando nmap a ser executado
            String command = "docker exec tester nmap -sU -pU:" + port + " -Pn -n --script=ntp-monlist " + ip;

            // Executa o comando
            ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
            Process process = processBuilder.start();

            // Captura a saída do comando
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            boolean isVulnerable = false;

            // Analisa a saída
            while ((line = reader.readLine()) != null) {
                if (line.contains("ntp-monlist")) {
                    isVulnerable = true;
                    break;
                }
            }

            process.waitFor();

            var vulnerabilityTestResponse = createResponse(vulnerability);
            if (isVulnerable){
                vulnerabilityTestResponse.setTestStatus(TestStatus.VULNERABLE);
                vulnerabilityTestResponse.setTestResultMessage("O serviço NTP está vulnerável a ataques de amplificação DDOS. O modo 6 do NTP está exposto e pode ser explorado por atacantes.");
            } else {
                vulnerabilityTestResponse.setTestStatus(TestStatus.NOT_VULNERABLE);
                vulnerabilityTestResponse.setTestResultMessage("O serviço NTP não está vulnerável a ataques de amplificação DDOS. O modo 6 do NTP não está exposto, o que mitiga a possibilidade de exploração.");
            }

            return vulnerabilityTestResponse;
        } catch (Exception e) {
            throw new FailedTestException(String.format("Failed to test NTP DDOS Amplification vulnerability: " + e.getMessage()), e);
        }
    }

}
