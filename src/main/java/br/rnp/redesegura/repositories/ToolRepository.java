package br.rnp.redesegura.repositories;

import br.rnp.redesegura.models.ActionLog;
import br.rnp.redesegura.models.Tool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToolRepository extends JpaRepository<Tool, Long> {
}
