package br.rnp.redesegura.controllers.api;


import br.rnp.redesegura.controllers.routes.Routes;
import br.rnp.redesegura.dto.ServiceDto;
import br.rnp.redesegura.dto.response.ServiceResponse;
import br.rnp.redesegura.services.ServiceEntityService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Routes.SERVICES)
public class ServiceController {

    @Autowired
    private ServiceEntityService serviceEntityService;

    @GetMapping
    public ResponseEntity<List<ServiceResponse>> getAll() {
        return ResponseEntity.ok(serviceEntityService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceResponse> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(serviceEntityService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ServiceResponse> create(@RequestBody @Valid ServiceDto serviceDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(serviceEntityService.create(serviceDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceResponse> update(@PathVariable("id") Long id,
                                                  @RequestBody @Valid ServiceDto serviceDto) {
        return ResponseEntity.ok(serviceEntityService.update(id, serviceDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        serviceEntityService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
