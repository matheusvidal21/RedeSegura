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

public class SambaOutdatedVersionTestStrategy implements VulnerabilityTestStrategy {
    @Override
    public VulnerabilityTestResponse test(Vulnerability vulnerability) {
        try {
            String ip = vulnerability.getService().getIp();

            // Primeiro comando tenta usar o protocolo mais recente
            String commandMaxProtocol = "wsl smbclient -L \\\\" + ip + " -N --option='client max protocol=SMB3'";
            // Comando de fallback para usar o protocolo mais antigo, se o primeiro falhar
            String commandMinProtocol = "wsl smbclient -L \\\\" + ip + " -N --option='client min protocol=NT1'";

            // Executa o primeiro comando
            ProcessBuilder processBuilder = new ProcessBuilder(commandMaxProtocol.split(" "));
            Process process = processBuilder.start();

            // Captura a saída do comando
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String output = reader.lines().collect(Collectors.joining("\n"));

            process.waitFor();

            // Verifica se o primeiro comando foi bem-sucedido
            boolean isVulnerable = process.exitValue() == 0 && output.contains("Sharename");

            // Se o primeiro comando falhar, tenta o comando de fallback
            if (!isVulnerable) {
                processBuilder = new ProcessBuilder(commandMinProtocol.split(" "));
                process = processBuilder.start();
                reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                output = reader.lines().collect(Collectors.joining("\n"));

                process.waitFor();

                isVulnerable = process.exitValue() == 0 && output.contains("Sharename");
            }

            var vulnerabilityTestResponse = createResponse(vulnerability);

            if (isVulnerable) {
                vulnerabilityTestResponse.setTestStatus(TestStatus.VULNERABLE);
                vulnerabilityTestResponse.setTestResultMessage("O serviço Samba está vulnerável, foi possível listar compartilhamentos usando SMB1. O servidor pode estar usando uma versão antiga ou configurado de forma insegura.");
            } else {
                vulnerabilityTestResponse.setTestStatus(TestStatus.NOT_VULNERABLE);
                vulnerabilityTestResponse.setTestResultMessage("O serviço Samba não está vulnerável. Não foi possível listar compartilhamentos com SMB1 ou o servidor está usando um protocolo mais seguro.");
            }

            return vulnerabilityTestResponse;

        } catch (Exception e) {
            throw new FailedTestException(String.format("Failed to test for Samba vulnerability: " + e.getMessage()), e);
        }
    }
}
