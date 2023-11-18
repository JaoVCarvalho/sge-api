package ufla.sge.api.domain.book;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ufla.sge.api.domain.subject.Subject;

@Entity
@Table(name = "books")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String edition;
    private String author;

    @ManyToOne
    private Subject subject;

    public Book(BookRegistrationDTO data, Subject subject){
        this.name = data.name();
        this.edition = data.edition();
        this.author = data.author();
        this.subject = subject;
    }

    public void update(BookUpdateDTO data, Subject subject){
        this.name = data.name();
        this.edition = data.edition();
        this.author = data.author();
        this.subject = subject;
    }

}
