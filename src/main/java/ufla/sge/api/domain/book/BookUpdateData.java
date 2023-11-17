package ufla.sge.api.domain.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BookUpdateData(
        @NotNull
        Integer id,
        @NotBlank
        String name,
        @NotBlank
        String edition,
        @NotBlank
        String author,
        @NotNull
        Integer subject_id) {
}
