package br.rnp.redesegura.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

@Component
public class MessageList {

    private static Properties properties;

    @Autowired
    public MessageList(){
        init();
    }

    private void init(){
        properties = new Properties();
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("messages.properties");
        try {
            properties.load(new InputStreamReader(in, "UTF-8"));
            in.close();
        } catch (IOException e){
            throw new RuntimeException("Erro ao ler o arquivo de mensagens: " + e.getMessage());
        }
    }

    public enum Message {

        DNS_RECURSION_REASON("vulnerability.dns_recursion.reason"),
        DNS_RECURSION_IMPORTANCE("vulnerability.dns_recursion.importance"),
        DNS_RECURSION_SOLUTION("vulnerability.dns_recursion.solution"),

        NTP_DDOS_AMPLIFICATION_REASON("vulnerability.ntp_ddos_amplification.reason"),
        NTP_DDOS_AMPLIFICATION_IMPORTANCE("vulnerability.ntp_ddos_amplification.importance"),
        NTP_DDOS_AMPLIFICATION_SOLUTION("vulnerability.ntp_ddos_amplification.solution");

        private final String value;

        Message(final String value) {
            this.value = value;
        }

        public String getValue() { return value; }
    }

    public static String getMessage(Message msg, Object... args){
        String message = properties.getProperty(msg.getValue());
        if (message == null) {
            System.out.println("Mensagem não encontrada para a chave: " + msg.getValue());
            throw new RuntimeException("Mensagem não encontrada para a chave: " + msg.getValue());
        }
        return String.format(message, args);
    }
}
