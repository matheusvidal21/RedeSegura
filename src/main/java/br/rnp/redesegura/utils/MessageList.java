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
        NTP_DDOS_AMPLIFICATION_SOLUTION("vulnerability.ntp_ddos_amplification.solution"),

        NETBIOS_EXPOSURE_REASON("vulnerability.netbios_exposure.reason"),
        NETBIOS_EXPOSURE_IMPORTANCE("vulnerability.netbios_exposure.importance"),
        NETBIOS_EXPOSURE_SOLUTION("vulnerability.netbios_exposure.solution"),

        SNMP_PUBLIC_COMMUNITY_REASON("vulnerability.snmp_public_community.reason"),
        SNMP_PUBLIC_COMMUNITY_IMPORTANCE("vulnerability.snmp_public_community.importance"),
        SNMP_PUBLIC_COMMUNITY_SOLUTION("vulnerability.snmp_public_community.solution"),

        SAMBA_OUTDATED_VERSION_REASON("vulnerability.samba_outdated_version.reason"),
        SAMBA_OUTDATED_VERSION_IMPORTANCE("vulnerability.samba_outdated_version.importance"),
        SAMBA_OUTDATED_VERSION_SOLUTION("vulnerability.samba_outdated_version.solution"),

        EXPOSED_MYSQL_REASON("vulnerability.exposed_mysql.reason"),
        EXPOSED_MYSQL_IMPORTANCE("vulnerability.exposed_mysql.importance"),
        EXPOSED_MYSQL_SOLUTION("vulnerability.exposed_mysql.solution"),

        REDIS_NO_AUTHENTICATION_REASON("vulnerability.redis_no_authentication.reason"),
        REDIS_NO_AUTHENTICATION_IMPORTANCE("vulnerability.redis_no_authentication.importance"),
        REDIS_NO_AUTHENTICATION_SOLUTION("vulnerability.redis_no_authentication.solution"),

        EXPOSED_SSDP_REASON("vulnerability.exposed_ssdp.reason"),
        EXPOSED_SSDP_IMPORTANCE("vulnerability.exposed_ssdp.importance"),
        EXPOSED_SSDP_SOLUTION("vulnerability.exposed_ssdp.solution"),

        EXPOSED_MEMCACHED_REASON("vulnerability.exposed_memcached.reason"),
        EXPOSED_MEMCACHED_IMPORTANCE("vulnerability.exposed_memcached.importance"),
        EXPOSED_MEMCACHED_SOLUTION("vulnerability.exposed_memcached.solution"),

        EXPOSED_SLP_REASON("vulnerability.exposed_slp.reason"),
        EXPOSED_SLP_IMPORTANCE("vulnerability.exposed_slp.importance"),
        EXPOSED_SLP_SOLUTION("vulnerability.exposed_slp.solution");

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
