package br.rnp.redesegura.mapper;

import br.rnp.redesegura.dto.ServerDto;
import br.rnp.redesegura.dto.response.ServerResponse;
import br.rnp.redesegura.models.Institution;
import br.rnp.redesegura.models.Server;
import br.rnp.redesegura.models.User;

public class ServerMapper {

    private ServerMapper() {
    }

    public static ServerResponse toResponse(Server serverEntity) {
        return ServerResponse.builder()
                .id(serverEntity.getId())
                .name(serverEntity.getName())
                .institutionName(serverEntity.getInstitution().getName())
                .responsibleName(serverEntity.getResponsible().getName())
                .build();
    }

    public static Server toEntity(ServerDto serverDto, Institution institution, User responsible) {
        return Server.builder()
                .name(serverDto.getName())
                .institution(institution)
                .responsible(responsible)
                .build();
    }
}
