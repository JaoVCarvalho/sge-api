package ufla.sge.api.domain.subject;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import ufla.sge.api.domain.teacher.Teacher;

public record SubjectRegistrationData(
        @NotBlank
        String name,
        @NotBlank
        String code,
        @NotNull
        Integer credits,
        @NotNull
        Integer teacher_id) {
}
