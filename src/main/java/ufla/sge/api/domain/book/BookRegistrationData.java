package ufla.sge.api.domain.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BookRegistrationData(
        @NotBlank
        String name,
        @NotBlank
        String edition,
        @NotBlank
        String author,
        @NotNull
        Integer subjects_id) {
}
