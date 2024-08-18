package br.rnp.redesegura.repositories;

import br.rnp.redesegura.models.Address;
import br.rnp.redesegura.models.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {
}
