package br.rnp.redesegura.controllers.api;

import br.rnp.redesegura.controllers.routes.Routes;
import br.rnp.redesegura.dto.InstitutionDto;
import br.rnp.redesegura.dto.response.InstitutionResponse;
import br.rnp.redesegura.services.InstitutionService;
import io.swagger.annotations.ApiOperation;
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
    @ApiOperation(value = "Obter todas as instituições", notes = "Retorna uma lista de todas as instituições.")
    public ResponseEntity<List<InstitutionResponse>> getAll() {
        return ResponseEntity.ok(institutionService.findAll());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obter instituição por ID", notes = "Retorna os detalhes de uma instituição específica pelo ID.")
    public ResponseEntity<InstitutionResponse> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(institutionService.findById(id));
    }

    @PostMapping
    @ApiOperation(value = "Criar nova instituição", notes = "Cria uma nova instituição com os dados fornecidos.")
    public ResponseEntity<InstitutionResponse> create(@RequestBody @Valid InstitutionDto institutionDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(institutionService.create(institutionDto));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Atualizar uma instituição", notes = "Atualiza uma instituição específica pelo ID com os dados fornecidos.")
    public ResponseEntity<InstitutionResponse> update(@PathVariable("id") Long id, @RequestBody @Valid InstitutionDto institutionDto) {
        return ResponseEntity.ok(institutionService.update(id, institutionDto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deletar uma instituição", notes = "Deleta uma instituição específica pelo ID.")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        institutionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
