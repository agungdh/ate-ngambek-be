package id.my.agungdh.atengambekbe.DTO;

import id.my.agungdh.atengambekbe.entity.Ngambek;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

import java.time.LocalDateTime;
import java.util.UUID;

public record SelesaiDTO(
        UUID id,
        UUID ngambek_id,
        LocalDateTime kapan,
        String gimana
) {
}
