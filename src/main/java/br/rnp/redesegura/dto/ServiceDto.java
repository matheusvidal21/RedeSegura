package br.rnp.redesegura.dto;

import br.rnp.redesegura.models.Protocol;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceDto {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "IP is required")
    private String ip;

    @NotNull(message = "Port is required")
    private Long port;

    @NotNull(message = "Protocols are required")
    private Set<String> protocols;

    @NotNull(message = "System ID is required")
    private Long systemId;


}