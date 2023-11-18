package ufla.sge.api.domain.subject;

import ufla.sge.api.domain.teacher.Teacher;

public record SubjectListingDTO(Integer id, String name, String code, Integer credits, Integer teacher_id, String teacher_name) {

    public SubjectListingDTO(Subject subject, Teacher teacher){
        this(subject.getId(), subject.getName(), subject.getCode(), subject.getCredits(), teacher.getId(), teacher.getName());
    }

}
