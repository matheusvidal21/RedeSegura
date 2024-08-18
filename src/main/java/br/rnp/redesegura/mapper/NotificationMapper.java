package br.rnp.redesegura.mapper;

import br.rnp.redesegura.dto.NotificationDto;
import br.rnp.redesegura.dto.response.NotificationResponse;
import br.rnp.redesegura.models.Notification;
import br.rnp.redesegura.models.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NotificationMapper {

    private NotificationMapper() {
    }

    public static NotificationResponse toResponse(Notification notification) {
        return NotificationResponse.builder()
                .id(notification.getId())
                .recipient(notification.getRecipient().getName())
                .message(notification.getMessage())
                .read(notification.getIsRead())
                .timestamp(notification.getTimestamp().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .build();
    }

    public static Notification toEntity(NotificationDto notificationDto, User recipient) {
        return Notification.builder()
                .recipient(recipient)
                .message(notificationDto.getMessage())
                .isRead(notificationDto.getRead())
                .timestamp(LocalDateTime.now())
                .build();
    }

}
