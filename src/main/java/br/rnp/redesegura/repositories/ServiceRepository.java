package br.rnp.redesegura.repositories;

import br.rnp.redesegura.models.Address;
import br.rnp.redesegura.models.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {

    @Query("SELECT s FROM Service s WHERE s.server.id = :serverId")
    List<Service> findAllByServerId(@Param("serverId") Long serverId);

    @Query("SELECT s FROM Service s WHERE s.server.institution.id = :institutionId")
    List<Service> findAllByInstitutionId(@Param("institutionId") Long institutionId);

    @Query("SELECT COUNT(s) FROM Service s WHERE s.status = 'DEGRADED'")
    int countDegradedServices();

    @Query("SELECT COUNT(s) FROM Service s WHERE s.status = 'DEGRADED' AND s.server.institution.id = :institutionId")
    int countDegradedServicesByInstitution(@Param("institutionId") Long institutionId);

    @Query("SELECT COUNT(s) FROM Service s WHERE s.status = 'HEALTHY'")
    int countHealthyServices();

    @Query("SELECT COUNT(s) FROM Service s WHERE s.status = 'HEALTHY' AND s.server.institution.id = :institutionId")
    int countHealthyServicesByInstitution(@Param("institutionId") Long institutionId);

}
