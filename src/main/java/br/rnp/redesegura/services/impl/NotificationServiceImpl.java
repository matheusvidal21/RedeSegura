package br.rnp.redesegura.services.impl;

import br.rnp.redesegura.dto.NotificationDto;
import br.rnp.redesegura.dto.response.NotificationResponse;
import br.rnp.redesegura.mapper.NotificationMapper;
import br.rnp.redesegura.models.Notification;
import br.rnp.redesegura.repositories.NotificationRepository;
import br.rnp.redesegura.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public List<NotificationResponse> findAll() {
        return notificationRepository.findAll().stream()
                .map(NotificationMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public NotificationResponse findById(Long id) {
        Notification notification = notificationRepository.findById(id).orElseThrow(() -> new RuntimeException("Notification not found"));
        return NotificationMapper.toResponse(notification);
    }

    @Override
    public NotificationResponse create(NotificationDto notificationDto) {
        Notification notification = NotificationMapper.toEntity(notificationDto);
        return NotificationMapper.toResponse(notificationRepository.save(notification));
    }

    @Override
    public NotificationResponse update(Long id, NotificationDto notificationDto) {
        Notification existingNotification = notificationRepository.findById(id).orElseThrow(() -> new RuntimeException("Notification not found"));
        Notification updatedNotification = NotificationMapper.toEntity(notificationDto);
        updatedNotification.setId(existingNotification.getId());
        return NotificationMapper.toResponse(notificationRepository.save(updatedNotification));
    }

    @Override
    public void delete(Long id) {
        notificationRepository.deleteById(id);
    }

}
