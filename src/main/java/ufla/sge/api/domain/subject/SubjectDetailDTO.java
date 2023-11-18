package ufla.sge.api.domain.subject;

public record SubjectDetailDTO(Integer id, String name, String code, Integer credits, Integer teacher_id) {

    public SubjectDetailDTO(Subject subject){
        this(subject.getId(), subject.getName(), subject.getCode(), subject.getCredits(), subject.getTeacher().getId());
    }
}
