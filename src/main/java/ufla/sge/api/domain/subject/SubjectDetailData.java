package ufla.sge.api.domain.subject;

import ufla.sge.api.domain.teacher.Teacher;

public record SubjectDetailData(Integer id, String name, String code, Integer credits, Integer teacher_id) {

    public SubjectDetailData(Subject subject){
        this(subject.getId(), subject.getName(), subject.getCode(), subject.getCredits(), subject.getTeacher().getId());
    }
}
