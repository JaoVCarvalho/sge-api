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
import ufla.sge.api.domain.book.*;
import ufla.sge.api.domain.subject.SubjectRepository;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    @Transactional
    public ResponseEntity<BookDetailDTO> registerBook(@RequestBody @Valid BookRegistrationDTO data, UriComponentsBuilder uriBuilder){
        var subject = subjectRepository.getReferenceById(data.subject_id());
        var book = new Book(data, subject);

        bookRepository.save(book);

        var uri = uriBuilder.path("/books/{id}").buildAndExpand(book.getId()).toUri();

        return  ResponseEntity.created(uri).body(new BookDetailDTO(book));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public ResponseEntity<List<BookListingDTO>> listBooks(){
        var books = bookRepository.findAllByBookWithSubjectName();

        return ResponseEntity.ok(books);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PatchMapping ("/{id}")
    @Transactional
    public ResponseEntity<BookDetailDTO> updateBook(@RequestBody @Valid BookUpdateDTO data, @PathVariable Integer id){
        var subject = subjectRepository.getReferenceById(data.subject_id());
        var book = bookRepository.getReferenceById(id);

        book.update(data, subject);

        return ResponseEntity.ok(new BookDetailDTO(book));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteBook(@PathVariable Integer id){
        bookRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
