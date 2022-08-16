package ru.bookstore.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long authorId;

    private String authorName;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "BOOK_AUTHOR",
            joinColumns = {@JoinColumn(name = "author_id")},
            inverseJoinColumns = {@JoinColumn(name = "book_id")})

    private Set<Book> books;

    public Author(String authorName) {
        this.authorName = authorName;
    }

    public Author(String authorName, Set<Book> books) {

        this.authorName = authorName;
        this.books = books;
    }

    public Set<Book> getBooks() {
        return books;
    }
}
