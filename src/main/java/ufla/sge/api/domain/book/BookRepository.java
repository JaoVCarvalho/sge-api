package ufla.sge.api.domain.book;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ufla.sge.api.domain.subject.SubjectListingData;

public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query("SELECT new ufla.sge.api.domain.book.BookListingData(b.name, b.edition, b.author, s.name) FROM Book b JOIN b.subjects s")
    Page<BookListingData> findAllByBookWithSubjectName(Pageable page);

}
