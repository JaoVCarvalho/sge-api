package ufla.sge.api.domain.book;

public record BookDetailDTO(Integer id, String name, String edition, String author, Integer subject_id) {
    public BookDetailDTO(Book book){
        this(book.getId(), book.getName(), book.getEdition(), book.getAuthor(), book.getSubject().getId());
    }
}
