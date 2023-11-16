package ufla.sge.api.domain.teacher;

public record TeacherListingData(String name, Department department, Graduated_in graduated_in, Education_level education_level) {
    public TeacherListingData(Teacher teacher){
        this(teacher.getName(), teacher.getDepartment(), teacher.getGraduated_in(), teacher.getEducation_level());
    }
}
