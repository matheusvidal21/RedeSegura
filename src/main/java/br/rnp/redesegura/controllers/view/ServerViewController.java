package br.rnp.redesegura.controllers.view;

import br.rnp.redesegura.controllers.routes.Routes;
import br.rnp.redesegura.dto.ServerDto;
import br.rnp.redesegura.dto.response.ServerResponse;
import br.rnp.redesegura.services.InstitutionService;
import br.rnp.redesegura.services.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(Routes.SERVERS_VIEW)
public class ServerViewController {

    @Autowired
    private ServerService serverService;

    @Autowired
    private InstitutionService institutionService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("servers", serverService.findAll());
        return "server/list";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable("id") Long id, Model model) {
        model.addAttribute("server", serverService.findById(id));
        return "server/detail";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("serverDto", new ServerDto());
        model.addAttribute("institutions", institutionService.findAll());
        return "server/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("serverDto") ServerDto serverDto) {
        serverService.create(serverDto);
        return "redirect:/servers";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable("id") Long id, Model model) {
        ServerResponse server = serverService.findById(id);
        ServerDto serverDto = ServerDto.builder()
                .id(id)
                .name(server.getName())
                .health(server.getHealth())
                .institutionId(server.getInstitutionId())
                .build();
        model.addAttribute("serverDto", serverDto);
        model.addAttribute("institutions", institutionService.findAll());
        model.addAttribute("institution", server.getInstitutionName());
        return "server/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, @ModelAttribute("serverDto") ServerDto serverDto) {
        serverService.update(id, serverDto);
        return "redirect:/servers";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        serverService.delete(id);
        return "redirect:/servers";
    }
}
