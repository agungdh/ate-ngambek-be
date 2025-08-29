package id.my.agungdh.atengambekbe.DTO;

import jakarta.persistence.Column;

import java.time.LocalDateTime;
import java.util.UUID;

public record NgambekDTO(
        UUID id,
        LocalDateTime kapan,
        String kenapa
) {
}
