package br.rnp.redesegura.services;

import br.rnp.redesegura.models.Service;
import br.rnp.redesegura.models.Vulnerability;
import br.rnp.redesegura.models.enums.Severity;

import java.util.List;

public interface DashboardService {

    int countAllInstitutions();

    int countResolvedVulnerabilities();

    int countNotResolvedVulnerabilities();

    int countOpenVulnerabilities();

    int countHighSeverityVulnerabilities();

    int countDegradedServers();

    int countOperationalServers();

    int countVulnerabilitiesByInstitution(Long institutionId);

    int countResolvedVulnerabilitiesByInstitution(Long institutionId);

    int countNotResolvedVulnerabilitiesByInstitution(Long institutionId);

    int countOpenVulnerabilitiesByInstitution(Long institutionId);

    int countHighSeverityVulnerabilitiesByInstitution(Long institutionId);

    List<Vulnerability> findHighSeverityVulnerabilitiesByInstitution(Long institutionId);

    List<Vulnerability> findOpenVulnerabilitiesByInstitution(Long institutionId);

    List<Vulnerability> findOpenVulnerabilitiesByType(String typeName);

    List<Vulnerability> findResolvedVulnerabilitiesByType(String typeName);

    List<Service> findAllServicesByInstitutionId(Long institutionId);

    int countOpenVulnerabilitiesBySeverity(Severity severity, Long institutionId);

    int countResolvedVulnerabilitiesBySeverity(Severity severity, Long institutionId);

    int countNotResolvedVulnerabilitiesBySeverity(Severity severity, Long institutionId);

    int countOperationalServers(Long institutionId);

    int countPartiallyOperationalServers(Long institutionId);

    int countDegradedServers(Long institutionId);
}