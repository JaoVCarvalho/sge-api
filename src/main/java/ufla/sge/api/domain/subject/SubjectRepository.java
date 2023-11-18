package ufla.sge.api.domain.subject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {

    @Query("SELECT new ufla.sge.api.domain.subject.SubjectListingDTO(s.id, s.name, s.code, s.credits, t.id, t.name) FROM Subject s JOIN s.teacher t")
    List<SubjectListingDTO> findAllSubjectsWithTeacherName();
}
