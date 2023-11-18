package ufla.sge.api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import ufla.sge.api.domain.subject.*;
import ufla.sge.api.domain.teacher.TeacherRepository;

import java.util.List;


@RestController
@RequestMapping("/subjects")
public class SubjectController {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    @Transactional
    public ResponseEntity<SubjectDetailDTO> registerSubject(@RequestBody @Valid SubjectRegistrationDTO data, UriComponentsBuilder uriBuilder){
        var teacher = teacherRepository.getReferenceById(data.teacher_id());
        var subject = new Subject(data, teacher);

        subjectRepository.save(subject);

        var uri = uriBuilder.path("/subjects/{id}").buildAndExpand(subject.getId()).toUri();

        return ResponseEntity.created(uri).body(new SubjectDetailDTO(subject));

    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public ResponseEntity<List<SubjectListingDTO>> listSubjects(){
        var subjects = subjectRepository.findAllSubjectsWithTeacherName();

        return ResponseEntity.ok(subjects);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PatchMapping ("/{id}")
    @Transactional
    public ResponseEntity<SubjectDetailDTO> updateSubject(@RequestBody @Valid SubjectUpdateDTO data, @PathVariable Integer id){
        var teacher = teacherRepository.getReferenceById(data.teacher_id());
        var subject = subjectRepository.getReferenceById(id);

        subject.update(data, teacher);

        return ResponseEntity.ok(new SubjectDetailDTO(subject));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteSubject(@PathVariable Integer id){
        subjectRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
