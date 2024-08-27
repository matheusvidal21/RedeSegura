package br.rnp.redesegura.controllers.view;

import br.rnp.redesegura.dto.ServerDto;
import br.rnp.redesegura.services.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/servers")
public class ServerViewController {

    @Autowired
    private ServerService serverService;

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
        return "server/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("serverDto") ServerDto serverDto) {
        serverService.create(serverDto);
        return "redirect:/servers";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("serverDto", serverService.findById(id));
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
