package br.rnp.redesegura.controllers;

import br.rnp.redesegura.controllers.routes.Routes;
import br.rnp.redesegura.models.Service;
import br.rnp.redesegura.models.Vulnerability;
import br.rnp.redesegura.services.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(Routes.DASHBOARD)
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/institutions/count")
    public ResponseEntity<Integer> countAllInstitutions() {
        int count = dashboardService.countAllInstitutions();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/vulnerabilities/count-resolved")
    public ResponseEntity<Integer> countResolvedVulnerabilities() {
        int count = dashboardService.countResolvedVulnerabilities();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/vulnerabilities/count-not-resolved")
    public ResponseEntity<Integer> countNotResolvedVulnerabilities() {
        int count = dashboardService.countNotResolvedVulnerabilities();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/vulnerabilities/count-open")
    public ResponseEntity<Integer> countOpenVulnerabilities() {
        int count = dashboardService.countOpenVulnerabilities();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/vulnerabilities/count-high-severity")
    public ResponseEntity<Integer> countHighSeverityVulnerabilities() {
        int count = dashboardService.countHighSeverityVulnerabilities();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/servers/count-degraded")
    public ResponseEntity<Integer> countDegradedServers() {
        int count = dashboardService.countDegradedServers();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/servers/count-operational")
    public ResponseEntity<Integer> countOperationalServers() {
        int count = dashboardService.countOperationalServers();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/vulnerabilities/institution/{institutionId}/count")
    public ResponseEntity<Integer> countVulnerabilitiesByInstitution(@PathVariable Long institutionId) {
        int count = dashboardService.countVulnerabilitiesByInstitution(institutionId);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/vulnerabilities/institution/{institutionId}/count-resolved")
    public ResponseEntity<Integer> countResolvedVulnerabilitiesByInstitution(@PathVariable Long institutionId) {
        int count = dashboardService.countResolvedVulnerabilitiesByInstitution(institutionId);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/vulnerabilities/institution/{institutionId}/count-not-resolved")
    public ResponseEntity<Integer> countNotResolvedVulnerabilitiesByInstitution(@PathVariable Long institutionId) {
        int count = dashboardService.countNotResolvedVulnerabilitiesByInstitution(institutionId);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/vulnerabilities/institution/{institutionId}/count-open")
    public ResponseEntity<Integer> countOpenVulnerabilitiesByInstitution(@PathVariable Long institutionId) {
        int count = dashboardService.countOpenVulnerabilitiesByInstitution(institutionId);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/vulnerabilities/institution/{institutionId}/count-high-severity")
    public ResponseEntity<Integer> countHighSeverityVulnerabilitiesByInstitution(@PathVariable Long institutionId) {
        int count = dashboardService.countHighSeverityVulnerabilitiesByInstitution(institutionId);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/vulnerabilities/institution/{institutionId}/high-severity")
    public ResponseEntity<List<Vulnerability>> findHighSeverityVulnerabilitiesByInstitution(@PathVariable Long institutionId) {
        List<Vulnerability> vulnerabilities = dashboardService.findHighSeverityVulnerabilitiesByInstitution(institutionId);
        return ResponseEntity.ok(vulnerabilities);
    }

    @GetMapping("/vulnerabilities/institution/{institutionId}/open")
    public ResponseEntity<List<Vulnerability>> findOpenVulnerabilitiesByInstitution(@PathVariable Long institutionId) {
        List<Vulnerability> vulnerabilities = dashboardService.findOpenVulnerabilitiesByInstitution(institutionId);
        return ResponseEntity.ok(vulnerabilities);
    }

    @GetMapping("/vulnerabilities/type/{typeName}/open")
    public ResponseEntity<List<Vulnerability>> findOpenVulnerabilitiesByType(@PathVariable String typeName) {
        List<Vulnerability> vulnerabilities = dashboardService.findOpenVulnerabilitiesByType(typeName);
        return ResponseEntity.ok(vulnerabilities);
    }

    @GetMapping("/vulnerabilities/type/{typeName}/resolved")
    public ResponseEntity<List<Vulnerability>> findResolvedVulnerabilitiesByType(@PathVariable String typeName) {
        List<Vulnerability> vulnerabilities = dashboardService.findResolvedVulnerabilitiesByType(typeName);
        return ResponseEntity.ok(vulnerabilities);
    }

    @GetMapping("/services/institution/{institutionId}")
    public ResponseEntity<List<Service>> findAllServicesByInstitutionId(@PathVariable Long institutionId) {
        List<Service> services = dashboardService.findAllServicesByInstitutionId(institutionId);
        return ResponseEntity.ok(services);
    }
}
