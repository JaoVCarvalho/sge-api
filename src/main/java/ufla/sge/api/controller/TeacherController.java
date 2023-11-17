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
import ufla.sge.api.domain.teacher.*;

import java.util.ArrayList;
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
    public ResponseEntity<TeacherDetailData> registerTeacher(@RequestBody @Valid TeacherRegistrationData data, UriComponentsBuilder uriBuilder) {
        var teacher = new Teacher(data);
        repository.save(teacher);

        var uri = uriBuilder.path("/teachers/{id}").buildAndExpand(teacher.getId()).toUri();

        return ResponseEntity.created(uri).body(new TeacherDetailData(teacher));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public ResponseEntity<List<TeacherListingData>> listTeachers() {

        List<TeacherListingData> teachers = repository.findAll().stream().map(TeacherListingData::new).collect(Collectors.toList());

        return ResponseEntity.ok(teachers);
    }

//    @CrossOrigin(origins = "*", allowedHeaders = "*")
//    @GetMapping("/{id}")
//    public ResponseEntity<TeacherListingData> listTeacherById(@PathVariable Integer id) {
//        var teacher = repository.getReferenceById(id);
//
//        return ResponseEntity.ok(new TeacherListingData(teacher));
//    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PatchMapping("/{id}")
    @Transactional
    public ResponseEntity<TeacherDetailData> updateTeacher(@RequestBody @Valid TeacherUpdateData data, @PathVariable Integer id){
        var teacher = repository.getReferenceById(id);
        teacher.update(data);

        return ResponseEntity.ok(new TeacherDetailData(teacher));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteTeacher(@PathVariable Integer id){
        repository.deleteById(id);

        return ResponseEntity.noContent().build();

    }
}
