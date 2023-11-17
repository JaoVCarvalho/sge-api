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

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<BookDetailData> registerBook(@RequestBody @Valid BookRegistrationData data, UriComponentsBuilder uriBuilder){
        var subject = subjectRepository.getReferenceById(data.subjects_id());
        var book = new Book(data, subject);

        bookRepository.save(book);

        var uri = uriBuilder.path("/books/{id}").buildAndExpand(book.getId()).toUri();

        return  ResponseEntity.created(uri).body(new BookDetailData(book));
    }

    @GetMapping
    public ResponseEntity<Page<BookListingData>> listBooks(@PageableDefault(size = 10, sort = "name") Pageable page){
        var aux = bookRepository.findAllByBookWithSubjectName(page);

        return ResponseEntity.ok(aux);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookListingData> listBook(@PathVariable Integer id){
        var book = bookRepository.getReferenceById(id);

        return ResponseEntity.ok(new BookListingData(book));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<BookDetailData> updateBook(@RequestBody @Valid BookUpdateData data){
        var subject = subjectRepository.getReferenceById(data.subject_id());
        var book = bookRepository.getReferenceById(data.id());

        book.update(data, subject);

        return ResponseEntity.ok(new BookDetailData(book));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteBook(@PathVariable Integer id){
        bookRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
