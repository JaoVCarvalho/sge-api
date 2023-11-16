package ufla.sge.api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import ufla.sge.api.domain.subject.*;
import ufla.sge.api.domain.teacher.TeacherRepository;


@RestController
@RequestMapping("/subjects")
public class SubjectController {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<SubjectDetailData> registerSubject(@RequestBody @Valid SubjectRegistrationData data, UriComponentsBuilder uriBuilder){
        var teacher = teacherRepository.getReferenceById(data.teacher_id());
        var subject = new Subject(data, teacher);

        subjectRepository.save(subject);

        var uri = uriBuilder.path("/subjects/{id}").buildAndExpand(subject.getId()).toUri();

        return ResponseEntity.created(uri).body(new SubjectDetailData(subject));

    }

    @GetMapping
    public ResponseEntity<Page<SubjectListingData>> listSubjects(@PageableDefault(size = 10,sort = "name")Pageable page){
        var aux = subjectRepository.findAllSubjectsWithTeacherName(page);

        return ResponseEntity.ok(aux);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubjectListingData> listSubjectById(@PathVariable Integer id){
        var subject = subjectRepository.getReferenceById(id);

        return ResponseEntity.ok(new SubjectListingData(subject, subject.getTeacher()));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<SubjectDetailData> updateSubject(@RequestBody @Valid SubjectUpdateData data){
        var teacher = teacherRepository.getReferenceById(data.teacher_id());
        var subject = subjectRepository.getReferenceById(data.id());

        subject.update(data, teacher);

        return ResponseEntity.ok(new SubjectDetailData(subject));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteSubject(@PathVariable Integer id){
        subjectRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
