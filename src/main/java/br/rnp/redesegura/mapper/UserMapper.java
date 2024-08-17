package br.rnp.redesegura.mapper;

import br.rnp.redesegura.dto.UserDto;
import br.rnp.redesegura.dto.response.UserResponse;
import br.rnp.redesegura.models.Institution;
import br.rnp.redesegura.models.Role;
import br.rnp.redesegura.models.User;import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;
import java.util.stream.Collectors;

public class UserMapper {

    private UserMapper() {
    }

    public static UserResponse toResponse(User user){
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .roles(user.getRoles().stream().map(RoleMapper::toResponse).collect(Collectors.toList()))
                .institutionName(user.getInstitution().getName())
                .build();
    }

    public static User toEntity(UserDto userDto, Institution institution, Set<Role> roles){
        return User.builder()
                .email(userDto.getEmail())
                .name(userDto.getName())
                .institution(institution)
                .roles(roles)
                .password(userDto.getPassword())
                .build();
    }

}
