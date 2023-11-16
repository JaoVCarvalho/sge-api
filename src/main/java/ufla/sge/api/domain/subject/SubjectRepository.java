package ufla.sge.api.domain.subject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {

    @Query("SELECT new ufla.sge.api.domain.subject.SubjectListingData(s.name, s.code, s.credits, t.name) FROM Subject s JOIN s.teacher t")
    Page<SubjectListingData> findAllSubjectsWithTeacherName(Pageable page);
}
