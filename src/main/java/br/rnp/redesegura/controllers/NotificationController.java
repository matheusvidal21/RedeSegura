package br.rnp.redesegura.controllers;

import br.rnp.redesegura.controllers.routes.Routes;
import br.rnp.redesegura.dto.NotificationDto;
import br.rnp.redesegura.dto.response.NotificationResponse;
import br.rnp.redesegura.services.NotificationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Routes.NOTIFICATIONS)
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping
    public ResponseEntity<List<NotificationResponse>> getAll() {
        return ResponseEntity.ok(notificationService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificationResponse> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(notificationService.findById(id));
    }

    @PostMapping
    public ResponseEntity<NotificationResponse> create(@RequestBody @Valid NotificationDto notificationDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(notificationService.create(notificationDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<NotificationResponse> update(@PathVariable("id") Long id, @RequestBody @Valid NotificationDto notificationDto) {
        return ResponseEntity.ok(notificationService.update(id, notificationDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        notificationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
