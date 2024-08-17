package br.rnp.redesegura.mapper;

import br.rnp.redesegura.dto.SystemDto;
import br.rnp.redesegura.dto.response.SystemResponse;
import br.rnp.redesegura.models.Institution;
import br.rnp.redesegura.models.System;
import br.rnp.redesegura.models.User;

public class SystemMapper {

    private SystemMapper() {
    }

    public static SystemResponse toResponse(System systemEntity) {
        return SystemResponse.builder()
                .id(systemEntity.getId())
                .name(systemEntity.getName())
                .institutionName(systemEntity.getInstitution().getName())
                .responsibleName(systemEntity.getResponsible().getName())
                .build();
    }

    public static System toEntity(SystemDto systemDto, Institution institution, User responsible) {
        return System.builder()
                .name(systemDto.getName())
                .institution(institution)
                .responsible(responsible)
                .build();
    }
}
