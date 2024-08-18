package br.rnp.redesegura.controllers.api;

import br.rnp.redesegura.controllers.routes.Routes;
import br.rnp.redesegura.dto.UpdateUserDto;
import br.rnp.redesegura.dto.UserDto;
import br.rnp.redesegura.dto.response.UserResponse;
import br.rnp.redesegura.models.User;
import br.rnp.redesegura.services.InstitutionService;
import br.rnp.redesegura.services.RoleService;
import br.rnp.redesegura.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Routes.USERS)
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private InstitutionService institutionService;

    @Autowired
    private RoleService roleService;

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAll(){
        return ResponseEntity.ok(this.userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getById(@PathVariable("id") Long id){
        return ResponseEntity.ok(this.userService.findById(id));
    }

    @PostMapping
    public ResponseEntity<UserResponse> save(@RequestBody @Valid UserDto user){
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(this.userService.create(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        this.userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(@PathVariable("id") Long id, @RequestBody @Valid UpdateUserDto user){
        return ResponseEntity.ok(this.userService.update(id, user));
    }

}
