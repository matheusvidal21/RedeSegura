package br.rnp.redesegura.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressResponse {

    private Long id;

    private String street;

    private String city;

    private String state;

    private String country;

    private String postalCode;

    public String getFullAddress() {
        return String.format("%s, %s - %s, %s, CEP: %s",
                street, city, state, country, postalCode);
    }

}