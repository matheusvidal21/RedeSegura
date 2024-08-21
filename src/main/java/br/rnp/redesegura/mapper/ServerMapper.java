package br.rnp.redesegura.mapper;

import br.rnp.redesegura.dto.ServerDto;
import br.rnp.redesegura.dto.response.ServerResponse;
import br.rnp.redesegura.models.Institution;
import br.rnp.redesegura.models.Server;

public class ServerMapper {

    private ServerMapper() {
    }

    public static ServerResponse toResponse(Server serverEntity) {
        return ServerResponse.builder()
                .id(serverEntity.getId())
                .name(serverEntity.getName())
                .institutionName(serverEntity.getInstitution().getName())
                .build();
    }

    public static Server toEntity(ServerDto serverDto, Institution institution) {
        return Server.builder()
                .name(serverDto.getName())
                .institution(institution)
                .build();
    }
}
