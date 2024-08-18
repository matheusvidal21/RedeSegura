package br.rnp.redesegura.dto.response;

import br.rnp.redesegura.models.Protocol;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceResponse {

    private Long id;

    private String name;

    private String ip;

    private Long port;

    private Set<String> protocol;

    private String status;

    private String systemName;

}