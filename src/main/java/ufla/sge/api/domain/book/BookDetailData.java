package ufla.sge.api.domain.book;

public record BookDetailData(Integer id, String name, String edition, String author, Integer subjects_id) {
    public BookDetailData (Book book){
        this(book.getId(), book.getName(), book.getEdition(), book.getAuthor(), book.getSubjects().getId());
    }
}
