package br.rnp.redesegura.services.impl;

import br.rnp.redesegura.models.Service;
import br.rnp.redesegura.models.Vulnerability;
import br.rnp.redesegura.models.enums.Severity;
import br.rnp.redesegura.repositories.*;
import br.rnp.redesegura.services.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class DashboardServiceImpl implements DashboardService {


    @Autowired
    private InstitutionRepository institutionRepository;

    @Autowired
    private VulnerabilityRepository vulnerabilityRepository;

    @Autowired
    private ServerRepository serverRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Override
    public int countAllInstitutions() {
        return institutionRepository.countAllInstitutions();
    }

    @Override
    public int countResolvedVulnerabilities() {
        return vulnerabilityRepository.countResolvedVulnerabilities();
    }

    @Override
    public int countNotResolvedVulnerabilities() {
        return vulnerabilityRepository.countNotResolvedVulnerabilities();
    }

    @Override
    public int countOpenVulnerabilities() {
        return vulnerabilityRepository.countOpenVulnerabilities();
    }

    @Override
    public int countHighSeverityVulnerabilities() {
        return vulnerabilityRepository.countHighSeverityVulnerabilities();
    }

    @Override
    public int countDegradedServers() {
        return serverRepository.countDegradedServers();
    }

    @Override
    public int countOperationalServers() {
        return serverRepository.countOperationalServers();
    }

    @Override
    public int countVulnerabilitiesByInstitution(Long institutionId) {
        return vulnerabilityRepository.countVulnerabilitiesByInstitution(institutionId);
    }

    @Override
    public int countResolvedVulnerabilitiesByInstitution(Long institutionId) {
        return vulnerabilityRepository.countResolvedVulnerabilitiesByInstitution(institutionId);
    }

    @Override
    public int countNotResolvedVulnerabilitiesByInstitution(Long institutionId) {
        return vulnerabilityRepository.countNotResolvedVulnerabilitiesByInstitution(institutionId);
    }

    @Override
    public int countOpenVulnerabilitiesByInstitution(Long institutionId) {
        return vulnerabilityRepository.countOpenVulnerabilitiesByInstitution(institutionId);
    }

    @Override
    public int countHighSeverityVulnerabilitiesByInstitution(Long institutionId) {
        return vulnerabilityRepository.countHighSeverityVulnerabilitiesByInstitution(institutionId);
    }

    @Override
    public List<Vulnerability> findHighSeverityVulnerabilitiesByInstitution(Long institutionId) {
        return vulnerabilityRepository.findHighSeverityVulnerabilitiesByInstitution(institutionId);
    }

    @Override
    public List<Vulnerability> findOpenVulnerabilitiesByInstitution(Long institutionId) {
        return vulnerabilityRepository.findOpenVulnerabilitiesByInstitution(institutionId);
    }

    @Override
    public List<Vulnerability> findOpenVulnerabilitiesByType(String typeName) {
        return vulnerabilityRepository.findOpenVulnerabilitiesByType(typeName);
    }

    @Override
    public List<Vulnerability> findResolvedVulnerabilitiesByType(String typeName) {
        return vulnerabilityRepository.findResolvedVulnerabilitiesByType(typeName);
    }

    @Override
    public List<Service> findAllServicesByInstitutionId(Long institutionId) {
        return serviceRepository.findAllByInstitutionId(institutionId);
    }

    @Override
    public int countOpenVulnerabilitiesBySeverity(Severity severity, Long institutionId) {
        return vulnerabilityRepository.countOpenVulnerabilitiesBySeverity(severity, institutionId);
    }

    @Override
    public int countResolvedVulnerabilitiesBySeverity(Severity severity, Long institutionId) {
        return vulnerabilityRepository.countResolvedVulnerabilitiesBySeverity(severity, institutionId);
    }

    @Override
    public int countNotResolvedVulnerabilitiesBySeverity(Severity severity, Long institutionId) {
        return vulnerabilityRepository.countNotResolvedVulnerabilitiesBySeverity(severity, institutionId);
    }

    @Override
    public int countOperationalServers(Long institutionId) {
        return serverRepository.countOperationalServers(institutionId);
    }

    @Override
    public int countPartiallyOperationalServers(Long institutionId) {
        return serverRepository.countPartiallyOperationalServers(institutionId);
    }

    @Override
    public int countDegradedServers(Long institutionId) {
        return serverRepository.countDegradedServers(institutionId);
    }
}
