package br.rnp.redesegura.services.impl;

import br.rnp.redesegura.exception.NotFoundException;
import br.rnp.redesegura.models.Role;
import br.rnp.redesegura.repositories.RoleRepository;
import br.rnp.redesegura.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role findById(Long id) {
        return roleRepository.findById(id).orElseThrow(() -> new NotFoundException("Role not found"));
    }

    @Override
    public List<Role> findAllByIds(List<Long> ids) {
        List<Role> roles = roleRepository.findAllById(ids);

        if (roles.isEmpty()){
            throw new NotFoundException("Roles not found");
        }

        return roles;
    }
}
