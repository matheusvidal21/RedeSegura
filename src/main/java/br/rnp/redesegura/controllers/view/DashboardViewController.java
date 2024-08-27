package br.rnp.redesegura.controllers.view;


import br.rnp.redesegura.services.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
public class DashboardViewController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping
    public String getDashboard(Model model) {
        model.addAttribute("totalInstitutions", dashboardService.countAllInstitutions());
        model.addAttribute("totalServers", dashboardService.countDegradedServers() + dashboardService.countOperationalServers());
        model.addAttribute("totalServices", dashboardService.findAllServicesByInstitutionId(null).size()); // Adjust this as needed
        model.addAttribute("totalVulnerabilities", dashboardService.countOpenVulnerabilities());
        return "dashboard";
    }
}
