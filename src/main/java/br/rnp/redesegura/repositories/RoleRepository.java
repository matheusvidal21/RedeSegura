package br.rnp.redesegura.repositories;

import br.rnp.redesegura.models.Report;
import br.rnp.redesegura.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
