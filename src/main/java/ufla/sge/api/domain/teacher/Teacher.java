package ufla.sge.api.domain.teacher;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "teachers")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Department department;

    @Enumerated(EnumType.STRING)
    private Graduated_in graduated_in;

    @Enumerated(EnumType.STRING)
    private Education_level education_level;

    public Teacher(TeacherRegistrationData data){
        this.name = data.name();
        this.department = data.department();
        this.graduated_in = data.graduated_in();
        this.education_level = data.education_level();

    }

    public void update(TeacherUpdateData data){
        this.name = data.name();
        this.department = data.department();
        this.graduated_in = data.graduated_in();
        this.education_level = data.education_level();
    }

}
