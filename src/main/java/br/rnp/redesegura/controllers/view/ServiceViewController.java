package br.rnp.redesegura.controllers.view;

import br.rnp.redesegura.dto.ServiceDto;
import br.rnp.redesegura.services.ServiceEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/services")
public class ServiceViewController {

    @Autowired
    private ServiceEntityService serviceEntityService;

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
        return "service/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("serviceDto") ServiceDto serviceDto) {
        serviceEntityService.create(serviceDto);
        return "redirect:/services";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("serviceDto", serviceEntityService.findById(id));
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
