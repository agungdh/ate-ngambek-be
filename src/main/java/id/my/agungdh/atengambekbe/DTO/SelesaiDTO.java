package id.my.agungdh.atengambekbe.DTO;

import java.time.LocalDateTime;
import java.util.UUID;

public record SelesaiDTO(
        UUID id,
        UUID ngambek_id,
        LocalDateTime kapan,
        String gimana
) {
}
