package br.rnp.redesegura.controllers.view;

import br.rnp.redesegura.controllers.routes.Routes;
import br.rnp.redesegura.dto.InstitutionDto;
import br.rnp.redesegura.dto.response.InstitutionResponse;
import br.rnp.redesegura.services.InstitutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(Routes.INSTITUTIONS_VIEW)
public class InstitutionViewController {

    @Autowired
    private InstitutionService institutionService;

    @GetMapping
    public String listInstitutions(Model model) {
        List<InstitutionResponse> institutions = institutionService.findAll();
        model.addAttribute("institutions", institutions);
        return "institutions/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("institution", new InstitutionDto());
        return "institutions/create";
    }

    @PostMapping("/create")
    public String createInstitution(@ModelAttribute InstitutionDto institutionDto) {
        institutionService.create(institutionDto);
        return "redirect:/institutions";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        InstitutionResponse institution = institutionService.findById(id);
        model.addAttribute("institution", institution);
        return "institutions/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateInstitution(@PathVariable Long id, @ModelAttribute InstitutionDto institutionDto) {
        institutionService.update(id, institutionDto);
        return "redirect:/institutions";
    }

    @GetMapping("/delete/{id}")
    public String deleteInstitution(@PathVariable Long id) {
        institutionService.delete(id);
        return "redirect:/institutions";
    }
}