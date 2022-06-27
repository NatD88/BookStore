package ru.bookStore.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.bookStore.entity.Author;
import ru.bookStore.entity.Book;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class BookDTO {
    private Book book;
    private Set<Author> authors;
}
