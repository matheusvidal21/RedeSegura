package br.rnp.redesegura.controllers;


import br.rnp.redesegura.controllers.routes.Routes;
import br.rnp.redesegura.dto.SystemDto;
import br.rnp.redesegura.dto.response.SystemResponse;
import br.rnp.redesegura.services.SystemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Routes.SYSTEMS)
public class SystemController {

    @Autowired
    private SystemService systemService;

    @GetMapping
    public ResponseEntity<List<SystemResponse>> getAll() {
        return ResponseEntity.ok(systemService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SystemResponse> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(systemService.findById(id));
    }

    @PostMapping
    public ResponseEntity<SystemResponse> create(@RequestBody @Valid SystemDto systemDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(systemService.create(systemDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SystemResponse> update(@PathVariable("id") Long id, @RequestBody @Valid SystemDto systemDto) {
        return ResponseEntity.ok(systemService.update(id, systemDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        systemService.delete(id);
        return ResponseEntity.noContent().build();
    }
}