package ufla.sge.api.domain.book;

public record BookListingDTO(Integer id, String name, String edition, String author, Integer subject_id, String subject_name) {
    public BookListingDTO(Book book){
        this(book.getId(),book.getName(), book.getEdition(), book.getAuthor(), book.getSubject().getId(), book.getSubject().getName());
    }
}
