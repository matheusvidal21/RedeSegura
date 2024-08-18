package br.rnp.redesegura.controllers;

import br.rnp.redesegura.controllers.routes.Routes;
import br.rnp.redesegura.dto.ReportDto;
import br.rnp.redesegura.dto.response.ReportResponse;
import br.rnp.redesegura.services.ReportService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Routes.REPORTS)
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping
    public ResponseEntity<List<ReportResponse>> getAll() {
        return ResponseEntity.ok(reportService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReportResponse> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(reportService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ReportResponse> create(@RequestBody @Valid ReportDto reportDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reportService.create(reportDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReportResponse> update(@PathVariable("id") Long id, @RequestBody @Valid ReportDto reportDto) {
        return ResponseEntity.ok(reportService.update(id, reportDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        reportService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
