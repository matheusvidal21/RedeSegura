package br.rnp.redesegura.mapper;

import br.rnp.redesegura.dto.InstitutionDto;
import br.rnp.redesegura.dto.response.InstitutionResponse;
import br.rnp.redesegura.models.Address;
import br.rnp.redesegura.models.Institution;

public class InstitutionMapper {

    private InstitutionMapper() {
    }

    public static Institution toEntity(InstitutionDto institutionDto, Address address) {
        return Institution.builder()
                .name(institutionDto.getName())
                .address(address)
                .contact(institutionDto.getContact())
                .build();
    }

    public static InstitutionResponse toResponse(Institution institution) {
        return InstitutionResponse.builder()
                .id(institution.getId())
                .name(institution.getName())
                .address(AddressMapper.toResponse(institution.getAddress()))
                .contact(institution.getContact())
                .build();
    }

}
