package br.rnp.redesegura.dto;

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
public class SystemDto {

    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Version is required")
    private Long institutionId;

    @NotNull(message = "Version is required")
    private Long responsibleId;

}
