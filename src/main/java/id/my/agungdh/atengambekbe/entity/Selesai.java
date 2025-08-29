package id.my.agungdh.atengambekbe.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
public class Selesai {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private UUID uuid;
    @OneToOne
    @JoinColumn(name = "ngambek_id", nullable = false)
    private Ngambek ngambek;
    @Column(nullable = false)
    private LocalDateTime kapan;
    @Column(nullable = false)
    private String gimana;

    @PrePersist
    public void generateUuid() {
        if (uuid == null) {
            uuid = UUID.randomUUID();
        }
    }
}
