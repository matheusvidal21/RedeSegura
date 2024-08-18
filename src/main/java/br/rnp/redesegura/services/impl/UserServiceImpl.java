package br.rnp.redesegura.services.impl;

import br.rnp.redesegura.dto.LoginDto;
import br.rnp.redesegura.dto.UpdateUserDto;
import br.rnp.redesegura.dto.UserDto;
import br.rnp.redesegura.dto.response.UserResponse;
import br.rnp.redesegura.exception.BadRequestException;
import br.rnp.redesegura.exception.NotFoundException;
import br.rnp.redesegura.mapper.UserMapper;
import br.rnp.redesegura.models.Institution;
import br.rnp.redesegura.models.Role;
import br.rnp.redesegura.models.User;
import br.rnp.redesegura.repositories.InstitutionRepository;
import br.rnp.redesegura.repositories.RoleRepository;
import br.rnp.redesegura.repositories.UserRepository;
import br.rnp.redesegura.services.UserService;
import br.rnp.redesegura.utils.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private InstitutionRepository institutionRepository;

    @Autowired
    private RoleRepository roleRepository;


    @Override
    public List<UserResponse> findAll() {
        return this.userRepository.findAll().stream().map(UserMapper::toResponse).toList();
    }

    @Override
    public UserResponse findById(Long id) {
        User entity = this.userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
        return UserMapper.toResponse(entity);
    }

    @Override
    public UserResponse findByEmail(String email){
        User entity = this.userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("User with email not found"));
        return UserMapper.toResponse(entity);
    }


    @Override
    public UserResponse create(UserDto user) {
        validate(user);
        if( this.roleRepository.findAllById(user.getRolesIds()).size() != user.getRolesIds().size()){
            throw new BadRequestException("Roles not found");
        }
        Set<Role> roles = new HashSet<>(this.roleRepository.findAllById(user.getRolesIds()));
        Institution institution = this.institutionRepository.findById(user.getInstitutionId()).get();
        User entity = UserMapper.toEntity(user, institution, roles);
        entity.setPassword(PasswordUtils.encode(user.getPassword()));
        return UserMapper.toResponse(this.userRepository.save(entity));
    }

    @Override
    public void delete(Long id) {
        var user = this.userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
        this.userRepository.deleteById(id);
    }

    @Override
    public UserResponse update(Long id, UpdateUserDto user){
        User entity = this.userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));

        if (!entity.getEmail().equals(user.getEmail()) && this.userRepository.findByEmail(user.getEmail()).isPresent()){
            throw new BadRequestException("Email already in use");
        }

        if (this.institutionRepository.findById(user.getInstitutionId()).isEmpty()){
            throw new BadRequestException("Institution not found");
        }

        if( this.roleRepository.findAllById(user.getRolesIds()).size() != user.getRolesIds().size()){
            throw new BadRequestException("Roles not found");
        }
        Set<Role> roles = new HashSet<>(this.roleRepository.findAllById(user.getRolesIds()));

        entity.setName(user.getName());
        entity.setInstitution(this.institutionRepository.findById(user.getInstitutionId()).get());
        entity.setUpdatedAt(LocalDateTime.now());
        entity.setRoles(roles);
        entity.setEmail(user.getEmail());

        return UserMapper.toResponse(this.userRepository.save(entity));
    }

    @Override
    public boolean isLoginValid(LoginDto loginDto, User user) {
        return PasswordUtils.matches(loginDto.getPassword(), user.getPassword());
    }

    private void validate(UserDto user){
        if (this.userRepository.findByEmail(user.getEmail()).isPresent()){
            throw new BadRequestException("Email already in use");
        }

        if (this.institutionRepository.findById(user.getInstitutionId()).isEmpty()){
            throw new BadRequestException("Institution not found");
        }

        if (user.getRolesIds().isEmpty()){
            throw new BadRequestException("Roles not found");
        }

        if(roleRepository.findAllById(user.getRolesIds()).size() != user.getRolesIds().size()){
            throw new BadRequestException("Roles not found");
        }

    }
}
