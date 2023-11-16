package ufla.sge.api.domain.subject;

import ufla.sge.api.domain.teacher.Teacher;

public record SubjectListingData(String name, String code, Integer credits, String teacher_name) {

    public SubjectListingData(Subject subject, Teacher teacher){
        this(subject.getName(), subject.getCode(), subject.getCredits(), teacher.getName());
    }

}
