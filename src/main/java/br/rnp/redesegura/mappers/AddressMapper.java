package br.rnp.redesegura.mappers;

import br.rnp.redesegura.dtos.AddressDto;
import br.rnp.redesegura.dtos.response.AddressResponse;
import br.rnp.redesegura.models.Address;

public class AddressMapper {

    private AddressMapper() {
    }

    public static AddressResponse toResponse(Address address) {
        return AddressResponse.builder()
                .id(address.getId())
                .street(address.getStreet())
                .city(address.getCity())
                .state(address.getState())
                .country(address.getCountry())
                .postalCode(address.getPostalCode())
                .build();
    }

    public static Address toEntity(AddressDto addressDto) {
        return Address.builder()
                .street(addressDto.getStreet())
                .city(addressDto.getCity())
                .state(addressDto.getState())
                .country(addressDto.getCountry())
                .postalCode(addressDto.getPostalCode())
                .build();
    }

}
