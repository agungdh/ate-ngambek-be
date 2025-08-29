package id.my.agungdh.atengambekbe.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
public class Ngambek {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private UUID uuid;
    @Column(nullable = false)
    private LocalDateTime kapan;
    @Column(nullable = false)
    private String kenapa;

    @PrePersist
    public void generateUuid() {
        if (uuid == null) {
            uuid = UUID.randomUUID();
        }
    }
}
