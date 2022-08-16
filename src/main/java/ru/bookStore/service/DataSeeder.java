package ru.bookstore.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import ru.bookstore.entity.Author;
import ru.bookstore.entity.Book;
import ru.bookstore.repository.AuthorRepository;
import ru.bookstore.repository.BookRepository;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataSeeder implements ApplicationListener<ContextRefreshedEvent> {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        Book book1 = new Book("Animals", new BigDecimal(1500));
        Book book2 = new Book("Rats", new BigDecimal(700));
        Book book3 = new Book("Dogs", new BigDecimal(800));

        Author author1 = new Author("Ivanov");
        Author author2 = new Author("Petrov");
        Author author3 = new Author("Sidorov");

        Set<Author> authorSet = new HashSet<>();
        authorSet.add(author1);
        authorSet.add(author2);
        book1.setAuthors(authorSet);

        authorSet = new HashSet<>();
        authorSet.add(author1);
        book2.setAuthors(authorSet);

        authorSet = new HashSet<>();
        authorSet.add(author3);
        book3.setAuthors(authorSet);

        Set<Book> bookSet = new HashSet<>();
        bookSet.add(book1);
        bookSet.add(book2);
        author1.setBooks(bookSet);

        bookSet = new HashSet<>();
        bookSet.add(book1);
        author2.setBooks(bookSet);

        bookSet = new HashSet<>();
        bookSet.add(book3);
        author3.setBooks(bookSet);

        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);

        authorRepository.save(author1);
        authorRepository.save(author2);
        authorRepository.save(author3);
    }
}
