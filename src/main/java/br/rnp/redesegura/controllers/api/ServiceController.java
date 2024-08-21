package br.rnp.redesegura.controllers.api;


import br.rnp.redesegura.controllers.routes.Routes;
import br.rnp.redesegura.dto.ServiceDto;
import br.rnp.redesegura.dto.response.ServiceResponse;
import br.rnp.redesegura.services.ServiceEntityService;
import io.swagger.annotations.ApiOperation;
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
    @ApiOperation(value = "Obter todos os serviços", notes = "Retorna uma lista de todos os serviços registrados.")
    public ResponseEntity<List<ServiceResponse>> getAll() {
        return ResponseEntity.ok(serviceEntityService.findAll());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obter serviço por ID", notes = "Retorna os detalhes de um serviço específico pelo ID.")
    public ResponseEntity<ServiceResponse> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(serviceEntityService.findById(id));
    }

    @PostMapping
    @ApiOperation(value = "Criar novo serviço", notes = "Cria um novo serviço com os dados fornecidos.")
    public ResponseEntity<ServiceResponse> create(@RequestBody @Valid ServiceDto serviceDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(serviceEntityService.create(serviceDto));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Atualizar serviço por ID", notes = "Atualiza os detalhes de um serviço específico pelo ID.")
    public ResponseEntity<ServiceResponse> update(@PathVariable("id") Long id,
                                                  @RequestBody @Valid ServiceDto serviceDto) {
        return ResponseEntity.ok(serviceEntityService.update(id, serviceDto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deletar serviço por ID", notes = "Remove um serviço específico pelo ID.")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        serviceEntityService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
