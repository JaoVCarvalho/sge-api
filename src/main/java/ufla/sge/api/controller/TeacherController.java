package ufla.sge.api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import ufla.sge.api.domain.teacher.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    @Autowired
    private TeacherRepository repository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    @Transactional
    public ResponseEntity<TeacherDetailDTO> registerTeacher(@RequestBody @Valid TeacherRegistrationDTO data, UriComponentsBuilder uriBuilder) {
        Log log = Log.getInstance();

        var teacher = new Teacher(data);
        repository.save(teacher);

        var uri = uriBuilder.path("/teachers/{id}").buildAndExpand(teacher.getId()).toUri();
        log.log("Professor cadastrado!");
        return ResponseEntity.created(uri).body(new TeacherDetailDTO(teacher));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public ResponseEntity<List<TeacherListingDTO>> listTeachers() {
        var teachers = repository.findAll().stream().map(TeacherListingDTO::new).collect(Collectors.toList());

        return ResponseEntity.ok(teachers);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PatchMapping("/{id}")
    @Transactional
    public ResponseEntity<TeacherDetailDTO> updateTeacher(@RequestBody @Valid TeacherUpdateDTO data, @PathVariable Integer id){
        Log log = Log.getInstance();
        var teacher = repository.getReferenceById(id);
        teacher.update(data);
        log.log("Professor atualizado!");
        return ResponseEntity.ok(new TeacherDetailDTO(teacher));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteTeacher(@PathVariable Integer id){
        Log log = Log.getInstance();
        repository.deleteById(id);
        log.log("Professor deletado!");
        return ResponseEntity.noContent().build();

    }
}
