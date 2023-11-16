package ufla.sge.api.domain.subject;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import ufla.sge.api.domain.teacher.Teacher;

public record SubjectUpdateData(
        @NotNull
        Integer id,
        @NotBlank
        String name,
        @NotBlank
        String code,
        @NotNull
        Integer credits,
        @NotNull
        Integer teacher_id) {
}
