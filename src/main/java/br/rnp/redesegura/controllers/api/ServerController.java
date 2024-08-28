package br.rnp.redesegura.controllers.api;


import br.rnp.redesegura.controllers.routes.Routes;
import br.rnp.redesegura.dtos.ServerDto;
import br.rnp.redesegura.dtos.response.ServerResponse;
import br.rnp.redesegura.services.ServerService;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Routes.SERVERS)
public class ServerController {

    @Autowired
    private ServerService serverService;

    @GetMapping
    public ResponseEntity<List<ServerResponse>> getAll() {
        return ResponseEntity.ok(serverService.findAll());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obter todos os servidores", notes = "Retorna uma lista de todos os servidores.")
    public ResponseEntity<ServerResponse> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(serverService.findById(id));
    }

    @PostMapping
    @ApiOperation(value = "Obter servidor por ID", notes = "Retorna os detalhes de um servidor específico pelo ID.")
    public ResponseEntity<ServerResponse> create(@RequestBody @Valid ServerDto serverDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(serverService.create(serverDto));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Criar novo servidor", notes = "Cria um novo servidor com os dados fornecidos.")
    public ResponseEntity<ServerResponse> update(@PathVariable("id") Long id, @RequestBody @Valid ServerDto serverDto) {
        return ResponseEntity.ok(serverService.update(id, serverDto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deletar servidor", notes = "Deleta um servidor específico pelo ID.")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        serverService.delete(id);
        return ResponseEntity.noContent().build();
    }
}