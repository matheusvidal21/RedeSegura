package br.rnp.redesegura.models;

import br.rnp.redesegura.models.enums.ServiceStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "services")
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String ip;

    private Long port;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "service_protocols",
            joinColumns = @JoinColumn(name = "service_id"),
            inverseJoinColumns = @JoinColumn(name = "protocol_id")
    )
    private Set<Protocol> protocols = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private ServiceStatus status;

    @ManyToOne
    @JoinColumn(name = "server_id")
    private Server server;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
