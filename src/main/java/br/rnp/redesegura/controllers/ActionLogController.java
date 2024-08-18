package br.rnp.redesegura.controllers;

import br.rnp.redesegura.controllers.routes.Routes;
import br.rnp.redesegura.dto.ActionLogDto;
import br.rnp.redesegura.dto.response.ActionLogResponse;
import br.rnp.redesegura.services.ActionLogService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Routes.ACTION_LOGS)
public class ActionLogController {

    @Autowired
    private ActionLogService actionLogService;

    @GetMapping
    public ResponseEntity<List<ActionLogResponse>> getAll() {
        return ResponseEntity.ok(actionLogService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActionLogResponse> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(actionLogService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ActionLogResponse> create(@RequestBody @Valid ActionLogDto actionLogDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(actionLogService.create(actionLogDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ActionLogResponse> update(@PathVariable("id") Long id, @RequestBody @Valid ActionLogDto actionLogDto) {
        return ResponseEntity.ok(actionLogService.update(id, actionLogDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        actionLogService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
