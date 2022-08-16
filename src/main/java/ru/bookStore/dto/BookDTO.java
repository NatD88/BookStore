package ru.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.bookstore.entity.Author;
import ru.bookstore.entity.Book;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class BookDTO {
    private Book book;
    private Set<Author> authors;
}
