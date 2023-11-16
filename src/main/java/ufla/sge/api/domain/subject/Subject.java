package ufla.sge.api.domain.subject;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ufla.sge.api.domain.teacher.Teacher;

@Entity
@Table(name = "subjects")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String code;
    private Integer credits;

    @ManyToOne
    private Teacher teacher;

    public Subject(SubjectRegistrationData data, Teacher teacher){
        this.name = data.name();
        this.code = data.code();
        this.credits = data.credits();
        this.teacher = teacher;
    }

    public void update(SubjectUpdateData data, Teacher teacher){
        this.name = data.name();
        this.code = data.code();
        this.credits = data.credits();
        this.teacher = teacher;
    }
}
