package br.rnp.redesegura.controllers.view;


import br.rnp.redesegura.services.DashboardService;
import br.rnp.redesegura.services.InstitutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeViewController {


    @Autowired
    private DashboardService dashboardService;

    @Autowired
    private InstitutionService institutionService;

    @GetMapping("/")
    public String showHome(@RequestParam(required = false) Long institutionId, Model model) {
        model.addAttribute("institutions", institutionService.findAll());

        if (institutionId != null) {
            model.addAttribute("institutionId", institutionId);
            model.addAttribute("openVulnerabilities", dashboardService.countOpenVulnerabilitiesByInstitution(institutionId));
            model.addAttribute("resolvedVulnerabilities", dashboardService.countResolvedVulnerabilitiesByInstitution(institutionId));
            model.addAttribute("notResolvedVulnerabilities", dashboardService.countNotResolvedVulnerabilitiesByInstitution(institutionId));
            model.addAttribute("highSeverityVulnerabilities", dashboardService.countHighSeverityVulnerabilitiesByInstitution(institutionId));
            model.addAttribute("degradedServers", dashboardService.countDegradedServers(institutionId));
            model.addAttribute("operationalServers", dashboardService.countOperationalServers(institutionId));
        } else {
            model.addAttribute("totalInstitutions", dashboardService.countAllInstitutions());
            model.addAttribute("totalOpenVulnerabilities", dashboardService.countOpenVulnerabilities());
            model.addAttribute("totalResolvedVulnerabilities", dashboardService.countResolvedVulnerabilities());
            model.addAttribute("totalNotResolvedVulnerabilities", dashboardService.countNotResolvedVulnerabilities());
            model.addAttribute("totalHighSeverityVulnerabilities", dashboardService.countHighSeverityVulnerabilities());
            model.addAttribute("totalDegradedServers", dashboardService.countDegradedServers());
            model.addAttribute("totalOperationalServers", dashboardService.countOperationalServers());
        }

        return "home";
    }

    @GetMapping("/sobre-nos")
    public String showAboutUs(Model model) {
        return "about";
    }
}