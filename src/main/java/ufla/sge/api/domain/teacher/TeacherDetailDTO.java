package ufla.sge.api.domain.teacher;

public record TeacherDetailDTO(Integer id, String name, Department department, Graduated_in graduated_in, Education_level education_level) {
    public TeacherDetailDTO(Teacher teacher) {
        this(teacher.getId(), teacher.getName(), teacher.getDepartment(), teacher.getGraduated_in(), teacher.getEducation_level());
    }
}
