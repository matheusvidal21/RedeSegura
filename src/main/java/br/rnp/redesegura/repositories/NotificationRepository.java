package br.rnp.redesegura.repositories;

import br.rnp.redesegura.models.Notification;
import br.rnp.redesegura.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
