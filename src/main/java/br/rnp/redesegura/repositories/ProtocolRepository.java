package br.rnp.redesegura.repositories;

import br.rnp.redesegura.models.Protocol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProtocolRepository extends JpaRepository<Protocol, Long> {

    Optional<Protocol> findByName(String name);

}
