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

import java.util.List;

@Controller
@RequestMapping(Routes.SERVERS_VIEW)
public class ServerViewController {

    @Autowired
    private ServerService serverService;

    @Autowired
    private InstitutionService institutionService;

    @GetMapping
    public String listServers(Model model) {
        List<ServerResponse> servers = serverService.findAll();
        model.addAttribute("servers", servers);
        return "servers/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("server", new ServerDto());
        model.addAttribute("institutions", institutionService.findAll());
        return "servers/create";
    }

    @PostMapping("/create")
    public String createServer(@ModelAttribute ServerDto serverDto) {
        serverService.create(serverDto);
        return "redirect:/servers";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        ServerResponse server = serverService.findById(id);
        model.addAttribute("server", server);
        model.addAttribute("institutions", institutionService.findAll());
        return "servers/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateServer(@PathVariable Long id, @ModelAttribute ServerDto serverDto) {
        serverService.update(id, serverDto);
        return "redirect:/servers";
    }

    @GetMapping("/delete/{id}")
    public String deleteServer(@PathVariable Long id) {
        serverService.delete(id);
        return "redirect:/servers";
    }
}