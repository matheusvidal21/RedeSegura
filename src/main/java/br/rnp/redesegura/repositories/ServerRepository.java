package br.rnp.redesegura.repositories;

import br.rnp.redesegura.models.Server;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServerRepository extends JpaRepository<Server, Long> {

    @Query("SELECT s FROM Server s WHERE s.institution.id = :institutionId")
    List<Server> findAllByInstitutionId(@Param("institutionId") Long institutionId);

    @Query("SELECT COUNT(s) FROM Server s WHERE s.health = 'OPERATIONAL'")
    int countOperationalServers();

    @Query("SELECT COUNT(s) FROM Server s WHERE s.health = 'DEGRADED'")
    int countDegradedServers();

    @Query("SELECT COUNT(s) FROM Server s WHERE s.health = 'OPERATIONAL' AND s.institution.id = :institutionId")
    int countOperationalServersByInstitution(@Param("institutionId") Long institutionId);

    @Query("SELECT COUNT(s) FROM Server s WHERE s.health = 'DEGRADED' AND s.institution.id = :institutionId")
    int countDegradedServersByInstitution(@Param("institutionId") Long institutionId);

}
