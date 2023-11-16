package ufla.sge.api.domain.teacher;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TeacherUpdateData(
        @NotNull
        Integer id,
        @NotBlank
        String name,
        @NotNull
        Department department,
        @NotNull
        Graduated_in graduated_in,
        @NotNull
        Education_level education_level) {
}
