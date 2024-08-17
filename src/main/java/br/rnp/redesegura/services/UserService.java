package br.rnp.redesegura.services;

import br.rnp.redesegura.dto.LoginDto;
import br.rnp.redesegura.dto.UpdateUserDto;
import br.rnp.redesegura.dto.UserDto;
import br.rnp.redesegura.dto.response.UserResponse;
import br.rnp.redesegura.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserResponse> findAll();

    UserResponse findById(Long id);

    UserResponse findByEmail(String email);

    UserResponse create(UserDto user);

    void delete(Long id);

    UserResponse update(Long id, UpdateUserDto user);

    boolean isLoginValid(LoginDto loginDto, User user);


}
