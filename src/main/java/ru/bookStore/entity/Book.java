package ru.bookstore.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long bookId;

    private String title;

    @JsonIgnore
    @ManyToMany(mappedBy = "books")
    private Set<Author> authors;

    private BigDecimal price;

    public Book(String title, BigDecimal price) {
        this.title = title;
        this.price = price;
    }

    public Book(String title, BigDecimal price, Set<Author> authors) {
        this.title = title;
        this.price = price;
        this.authors = authors;
    }

    public Set<Author> getAuthors() {
        return authors;
    }
}
