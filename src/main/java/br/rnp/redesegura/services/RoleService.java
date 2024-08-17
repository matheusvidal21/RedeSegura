package br.rnp.redesegura.services;

import br.rnp.redesegura.models.Role;

import java.util.List;

public interface RoleService {

    List<Role> findAll();

    Role findById(Long id);

    List<Role> findAllByIds(List<Long> ids);

}
