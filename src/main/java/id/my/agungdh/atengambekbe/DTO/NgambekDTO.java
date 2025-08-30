package id.my.agungdh.atengambekbe.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public record NgambekDTO(
        @JsonProperty(access = JsonProperty.Access.READ_ONLY) UUID id,
        @NotNull
        LocalDateTime kapan,
        @NotBlank
        @Max(3)
        String kenapa
) {
}
