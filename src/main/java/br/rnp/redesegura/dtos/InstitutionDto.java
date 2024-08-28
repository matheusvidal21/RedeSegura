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
public class InstitutionDto {

    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Address is required")
    private AddressDto address;

    @NotBlank(message = "Contact is required")
    private String contact;

}