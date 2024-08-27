package br.rnp.redesegura.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServiceResponse {

    private Long id;

    private String name;

    private String ip;

    private Long port;

    private Set<String> protocols;

    private String status;

    private String serverName;

    private Long serverId;

}