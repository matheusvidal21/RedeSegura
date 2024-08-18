package br.rnp.redesegura.controllers;


import br.rnp.redesegura.controllers.routes.Routes;
import br.rnp.redesegura.dto.ToolDto;
import br.rnp.redesegura.dto.response.ToolResponse;
import br.rnp.redesegura.services.ToolService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Routes.TOOLS)
public class ToolController {

    @Autowired
    private ToolService toolService;

    @GetMapping
    public ResponseEntity<List<ToolResponse>> getAll() {
        return ResponseEntity.ok(toolService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ToolResponse> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(toolService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ToolResponse> create(@RequestBody @Valid ToolDto toolDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(toolService.create(toolDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ToolResponse> update(@PathVariable("id") Long id, @RequestBody @Valid ToolDto toolDto) {
        return ResponseEntity.ok(toolService.update(id, toolDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        toolService.delete(id);
        return ResponseEntity.noContent().build();
    }
}