package ufla.sge.api.domain.book;

public record BookListingData(String name, String edition, String author, String subject_name) {
    public BookListingData(Book book){
        this(book.getName(), book.getEdition(), book.getAuthor(), book.getSubjects().getName());
    }
}
