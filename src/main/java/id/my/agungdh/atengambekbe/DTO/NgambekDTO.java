package id.my.agungdh.atengambekbe.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;

import java.time.LocalDateTime;
import java.util.UUID;

public record NgambekDTO(
        @JsonProperty(access = JsonProperty.Access.READ_ONLY) UUID id,
        LocalDateTime kapan,
        String kenapa
) {
}
