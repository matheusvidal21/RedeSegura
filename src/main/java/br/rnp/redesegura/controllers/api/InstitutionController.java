package br.rnp.redesegura.controllers.api;

import br.rnp.redesegura.controllers.routes.Routes;
import br.rnp.redesegura.dto.InstitutionDto;
import br.rnp.redesegura.dto.response.InstitutionResponse;
import br.rnp.redesegura.services.InstitutionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Routes.INSTITUTIONS)
public class InstitutionController {

    @Autowired
    private InstitutionService institutionService;

    @GetMapping
    public ResponseEntity<List<InstitutionResponse>> getAll() {
        return ResponseEntity.ok(institutionService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InstitutionResponse> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(institutionService.findById(id));
    }

    @PostMapping
    public ResponseEntity<InstitutionResponse> create(@RequestBody @Valid InstitutionDto institutionDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(institutionService.create(institutionDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InstitutionResponse> update(@PathVariable("id") Long id, @RequestBody @Valid InstitutionDto institutionDto) {
        return ResponseEntity.ok(institutionService.update(id, institutionDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        institutionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
