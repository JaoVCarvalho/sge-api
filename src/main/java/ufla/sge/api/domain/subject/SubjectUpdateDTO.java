package ufla.sge.api.domain.subject;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SubjectUpdateDTO(
        @NotBlank
        String name,
        @NotBlank
        String code,
        @NotNull
        Integer credits,
        @NotNull
        Integer teacher_id) {
}
