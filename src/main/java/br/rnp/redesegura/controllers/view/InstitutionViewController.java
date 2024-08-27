package br.rnp.redesegura.controllers.view;

import br.rnp.redesegura.dto.InstitutionDto;
import br.rnp.redesegura.services.InstitutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/institutions")
public class InstitutionViewController {

    @Autowired
    private InstitutionService institutionService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("institutions", institutionService.findAll());
        return "institution/list";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable("id") Long id, Model model) {
        model.addAttribute("institution", institutionService.findById(id));
        return "institution/detail";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("institutionDto", new InstitutionDto());
        return "institution/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("institutionDto") InstitutionDto institutionDto) {
        institutionService.create(institutionDto);
        return "redirect:/institutions";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("institutionDto", institutionService.findById(id));
        return "institution/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, @ModelAttribute("institutionDto") InstitutionDto institutionDto) {
        institutionService.update(id, institutionDto);
        return "redirect:/institutions";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        institutionService.delete(id);
        return "redirect:/institutions";
    }

}
