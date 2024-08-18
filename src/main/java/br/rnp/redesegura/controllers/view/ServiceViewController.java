package br.rnp.redesegura.controllers.view;

import br.rnp.redesegura.controllers.routes.Routes;
import br.rnp.redesegura.dto.ServiceDto;
import br.rnp.redesegura.dto.response.ServiceResponse;
import br.rnp.redesegura.services.ServerService;
import br.rnp.redesegura.services.ServiceEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(Routes.SERVICES_VIEW)
public class ServiceViewController {

    @Autowired
    private ServiceEntityService serviceEntityService;

    @Autowired
    private ServerService serverService;

    @GetMapping
    public String listServices(Model model) {
        List<ServiceResponse> services = serviceEntityService.findAll();
        model.addAttribute("services", services);
        return "services/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("service", new ServiceDto());
        model.addAttribute("servers", serverService.findAll());
        return "services/create";
    }

    @PostMapping("/create")
    public String createService(@ModelAttribute ServiceDto serviceDto) {
        serviceEntityService.create(serviceDto);
        return "redirect:/services";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        ServiceResponse service = serviceEntityService.findById(id);
        model.addAttribute("service", service);
        model.addAttribute("servers", serverService.findAll());
        return "services/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateService(@PathVariable Long id, @ModelAttribute ServiceDto serviceDto) {
        serviceEntityService.update(id, serviceDto);
        return "redirect:/services";
    }

    @GetMapping("/delete/{id}")
    public String deleteService(@PathVariable Long id) {
        serviceEntityService.delete(id);
        return "redirect:/services";
    }
}