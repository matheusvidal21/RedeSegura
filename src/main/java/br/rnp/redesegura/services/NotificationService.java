package br.rnp.redesegura.services;

import br.rnp.redesegura.dto.NotificationDto;
import br.rnp.redesegura.dto.response.NotificationResponse;

import java.util.List;

public interface NotificationService {

    List<NotificationResponse> findAll();

    NotificationResponse findById(Long id);

    NotificationResponse create(NotificationDto notificationDto);

    NotificationResponse update(Long id, NotificationDto notificationDto);

    void delete(Long id);

}
