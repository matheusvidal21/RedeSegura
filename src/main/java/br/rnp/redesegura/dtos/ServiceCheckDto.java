package br.rnp.redesegura.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceCheckDto {

    @NotBlank(message = "IP is required")
    private String ip;

    @NotNull(message = "Port is required")
    private Long port;

}
