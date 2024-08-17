package br.rnp.redesegura.mapper;

import br.rnp.redesegura.dto.response.RoleResponse;
import br.rnp.redesegura.models.Role;

public class RoleMapper {

    private RoleMapper() {
    }

    public static RoleResponse toResponse(Role role){
        return RoleResponse.builder()
                .id(role.getId())
                .name(role.getName())
                .build();
    }

}
