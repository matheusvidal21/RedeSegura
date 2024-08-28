package br.rnp.redesegura.mappers;

import br.rnp.redesegura.dtos.ServerDto;
import br.rnp.redesegura.dtos.response.ServerResponse;
import br.rnp.redesegura.models.Institution;
import br.rnp.redesegura.models.Server;
import br.rnp.redesegura.models.enums.ServerHealth;

public class ServerMapper {

    private ServerMapper() {
    }

    public static ServerResponse toResponse(Server serverEntity) {
        return ServerResponse.builder()
                .id(serverEntity.getId())
                .name(serverEntity.getName())
                .institutionName(serverEntity.getInstitution().getName())
                .institutionId(serverEntity.getInstitution().getId())
                .health(serverEntity.getHealth().name())
                .createdAt(serverEntity.getCreatedAt() != null ? serverEntity.getCreatedAt().toString() : null)
                .updatedAt(serverEntity.getUpdatedAt() != null ? serverEntity.getUpdatedAt().toString() : null)
                .build();
    }

    public static Server toEntity(ServerDto serverDto, Institution institution) {
        return Server.builder()
                .name(serverDto.getName())
                .health(ServerHealth.fromValue(serverDto.getHealth()))
                .institution(institution)
                .build();
    }
}
