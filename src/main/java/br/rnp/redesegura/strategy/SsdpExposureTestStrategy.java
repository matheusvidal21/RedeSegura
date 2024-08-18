package br.rnp.redesegura.strategy;

import br.rnp.redesegura.dto.response.VulnerabilityTestResponse;
import br.rnp.redesegura.exception.FailedTestException;
import br.rnp.redesegura.models.Protocol;
import br.rnp.redesegura.models.Vulnerability;
import br.rnp.redesegura.models.enums.TestStatus;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

public class SsdpExposureTestStrategy implements VulnerabilityTestStrategy {

    private static final int STANDARD_SSDP_PORT = 1900;

    @Override
    public VulnerabilityTestResponse test(Vulnerability vulnerability) {
        try {
            String ip = vulnerability.getService().getIp();
            int port = STANDARD_SSDP_PORT;

            if (vulnerability.getService().getPort() != STANDARD_SSDP_PORT){
                port = Integer.parseInt(vulnerability.getService().getPort().toString());
            }

            // Monta a requisição SSDP
            String request = "M-SEARCH * HTTP/1.1\r\n" +
                    "HOST: 239.255.255.250:1900\r\n" +
                    "MAN: \"ssdp:discover\"\r\n" +
                    "MX: 3\r\n" +
                    "ST: ssdp:all\r\n\r\n";

            // Envia a requisição via UDP
            DatagramSocket socket = new DatagramSocket();
            InetAddress address = InetAddress.getByName(ip);
            DatagramPacket packet = new DatagramPacket(request.getBytes(), request.length(), address, port);
            socket.send(packet);

            // Recebe a resposta
            byte[] buffer = new byte[1024];
            DatagramPacket response = new DatagramPacket(buffer, buffer.length);
            socket.setSoTimeout(1000); // Timeout de 1 segundo para a resposta
            try {
                socket.receive(response);
                String responseStr = new String(response.getData(), 0, response.getLength());

                VulnerabilityTestResponse vulnerabilityTestResponse = VulnerabilityTestResponse.builder()
                        .vulnerabilityId(vulnerability.getId())
                        .vulnerabilityTitle(vulnerability.getTitle())
                        .service(vulnerability.getService().getName())
                        .ip(ip)
                        .port((long) port)
                        .protocols(vulnerability.getService().getProtocols().stream().map(Protocol::getName).collect(Collectors.toSet()))
                        .testedAt(LocalDateTime.now().toString())
                        .build();

                if (responseStr.contains("HTTP/1.1 200 OK")) {
                    vulnerabilityTestResponse.setTestStatus(TestStatus.VULNERABLE);
                    vulnerabilityTestResponse.setTestResultMessage("O serviço SSDP está exposto e vulnerável, permitindo possíveis ataques de amplificação DDoS.");
                } else {
                    vulnerabilityTestResponse.setTestStatus(TestStatus.NOT_VULNERABLE);
                    vulnerabilityTestResponse.setTestResultMessage("O serviço SSDP não está exposto, indicando que o serviço está seguro.");
                }

                return vulnerabilityTestResponse;
            } catch (Exception e) {
                return VulnerabilityTestResponse.builder()
                        .vulnerabilityId(vulnerability.getId())
                        .vulnerabilityTitle(vulnerability.getTitle())
                        .service(vulnerability.getService().getName())
                        .ip(ip)
                        .port((long) port)
                        .protocols(vulnerability.getService().getProtocols().stream().map(Protocol::getName).collect(Collectors.toSet()))
                        .testedAt(LocalDateTime.now().toString())
                        .testStatus(TestStatus.NOT_VULNERABLE)
                        .testResultMessage("O serviço SSDP não respondeu, indicando que o serviço está seguro ou a resposta foi filtrada.")
                        .build();
            } finally {
                socket.close();
            }
        } catch (Exception e) {
            throw new FailedTestException(String.format("Failed to test SSDP vulnerability: " + e.getMessage()), e);
        }
    }
}
