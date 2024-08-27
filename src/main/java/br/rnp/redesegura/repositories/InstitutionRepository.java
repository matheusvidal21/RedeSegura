package br.rnp.redesegura.repositories;

import br.rnp.redesegura.models.Institution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InstitutionRepository extends JpaRepository<Institution, Long> {

    @Query("SELECT COUNT(i) FROM Institution i")
    int countAllInstitutions();

    Optional<Institution> findByName(String name);

}
