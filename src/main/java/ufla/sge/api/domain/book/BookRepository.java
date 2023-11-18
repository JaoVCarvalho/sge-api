package ufla.sge.api.domain.book;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query("SELECT new ufla.sge.api.domain.book.BookListingDTO(b.id, b.name, b.edition, b.author, s.id, s.name) FROM Book b JOIN b.subject s")
    List<BookListingDTO> findAllByBookWithSubjectName();

}
