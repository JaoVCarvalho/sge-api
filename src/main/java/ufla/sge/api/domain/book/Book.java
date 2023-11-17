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
    private Subject subjects;

    public Book(BookRegistrationData data, Subject subjects){
        this.name = data.name();
        this.edition = data.edition();
        this.author = data.author();
        this.subjects = subjects;
    }

    public void update(BookUpdateData data, Subject subject){
        this.name = data.name();
        this.edition = data.edition();
        this.author = data.author();
        this.subjects = subject;
    }

}
