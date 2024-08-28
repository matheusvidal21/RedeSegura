package br.rnp.redesegura.controllers.view;

import br.rnp.redesegura.controllers.routes.Routes;
import br.rnp.redesegura.dtos.ServiceDto;
import br.rnp.redesegura.dtos.response.ServiceResponse;
import br.rnp.redesegura.models.enums.ServiceStatus;
import br.rnp.redesegura.services.ProtocolService;
import br.rnp.redesegura.services.ServerService;
import br.rnp.redesegura.services.ServiceEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;

@Controller
@RequestMapping(Routes.SERVICES_VIEW)
public class ServiceViewController {

    @Autowired
    private ServiceEntityService serviceEntityService;

    @Autowired
    private ServerService serverService;

    @Autowired
    private ProtocolService protocolService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("services", serviceEntityService.findAll());
        return "service/list";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable("id") Long id, Model model) {
        model.addAttribute("service", serviceEntityService.findById(id));
        return "service/detail";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("serviceDto", new ServiceDto());
        model.addAttribute("servers", serverService.findAll());
        model.addAttribute("protocols", protocolService.findAll());
        return "service/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("serviceDto") ServiceDto serviceDto) {
        serviceEntityService.create(serviceDto);
        return "redirect:/services";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable("id") Long id, Model model) {
        ServiceResponse serviceResponse = serviceEntityService.findById(id);
        ServiceDto serviceDto = ServiceDto.builder()
                .id(id)
                .ip(serviceResponse.getIp())
                .name(serviceResponse.getName())
                .protocols(serviceResponse.getProtocols() != null ? serviceResponse.getProtocols() : new HashSet<>())
                .port(serviceResponse.getPort())
                .status(ServiceStatus.fromString(serviceResponse.getStatus()).name())
                .serverId(serviceResponse.getServerId())
                .build();

        model.addAttribute("serviceDto", serviceDto);
        model.addAttribute("servers", serverService.findAll());
        model.addAttribute("protocols", protocolService.findAll());
        return "service/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, @ModelAttribute("serviceDto") ServiceDto serviceDto) {
        serviceEntityService.update(id, serviceDto);
        return "redirect:/services";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        serviceEntityService.delete(id);
        return "redirect:/services";
    }
}
