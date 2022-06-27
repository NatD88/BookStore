package ru.bookStore.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bookStore.dto.BookDTO;
import ru.bookStore.entity.Author;
import ru.bookStore.entity.Book;
import ru.bookStore.service.AuthorService;
import ru.bookStore.service.BookService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final AuthorService authorService;
    private final BookService bookService;

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks() {
        return new ResponseEntity<>(bookService.getBooks(), HttpStatus.OK);
    }

    @GetMapping("/authors")
    public ResponseEntity<List<Author>> getAllAuthors() {
        return new ResponseEntity<>(authorService.getAuthors(), HttpStatus.OK);
    }

    @GetMapping("/booksByAuthor")
    public ResponseEntity<Set<Book>> getAllAuthorBooks(Long authorId) {
        return new ResponseEntity<>(bookService.getBooksByAuthor(authorId), HttpStatus.OK);
    }

    @GetMapping("/allBookInfo")
    public ResponseEntity<BookDTO> getBookInfo(Long bookId) {
        return new ResponseEntity<>(bookService.getBookInfo(bookId), HttpStatus.OK);
    }

    @GetMapping("/allBookOfAutherPrice")
    public ResponseEntity<BigDecimal> getAllBookPrice(Long authorId) {
        return new ResponseEntity<>(bookService.getAllBookPrice(authorId), HttpStatus.OK);
    }
}
