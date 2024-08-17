package br.rnp.redesegura.repositories;

import br.rnp.redesegura.models.ActionLog;
import br.rnp.redesegura.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActionLogRepository extends JpaRepository<ActionLog, Long> {
}
