package ru.bookstore.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import ru.bookstore.dto.BookDTO;
import ru.bookstore.entity.Author;
import ru.bookstore.entity.Book;
import ru.bookstore.repository.AuthorRepository;
import ru.bookstore.repository.BookRepository;

import java.math.BigDecimal;
import java.util.*;

@SpringBootTest
class BookServiceTest {

    @Mock
    private AuthorRepository authorRepository;
    @Mock
    private BookRepository bookRepository;

    private BookService bookService;

    private Set<Author> autherSet;

    private Book testBook;

    private Set<Book> bookSet;

    private Author testAuthor;

    private List<Book> bookList;

    @BeforeEach
    void init() {
        bookService = new BookService(bookRepository, authorRepository);

        autherSet = new HashSet<Author>();
        Author author1 = new Author("Donald");
        Author author2 = new Author("King");
        autherSet.add(author1);
        autherSet.add(author2);

        bookSet = new HashSet<Book>();
        Book book1 = new Book("Dreams", new BigDecimal(1500));
        Book book2 = new Book("Goals", new BigDecimal(900));
        bookSet.add(book1);
        bookSet.add(book2);

        testBook = new Book("Hello, my friend", new BigDecimal(1500), autherSet);

        testAuthor = new Author("Kris", bookSet);

        bookList = new ArrayList<>();
        Book book3 = new Book("Colors", new BigDecimal(700));
        Book book4 = new Book("Fish", new BigDecimal(300));
        bookList.add(book3);
        bookList.add(book4);
    }

    @Test
    void getBooks() {
        Mockito.when(bookRepository.findAll()).thenReturn(bookList);
        List<Book> resList = bookService.getBooks();
        Assertions.assertEquals(bookList, resList);
    }

    @Test
    void getBooksByAuthor() {
        Optional<Author> optionalAuthor = Optional.of(testAuthor);
        Mockito.when(authorRepository.findById(Mockito.any())).thenReturn(optionalAuthor);
        Set<Book> booksByAuthor = bookService.getBooksByAuthor(2L);
        Assertions.assertEquals(bookSet.size(), booksByAuthor.size());
    }

    @Test
    void getBookInfo() {
        Optional<Book> optionalBook = Optional.of(testBook);
        Mockito.when(bookRepository.findById(Mockito.any())).thenReturn(optionalBook);
        BookDTO bookDTO = bookService.getBookInfo(3L);
        Assertions.assertEquals(testBook, bookDTO.getBook());
    }

    @Test
    void getAllBookPrice() {
        Optional<Author> optionalAuthor = Optional.of(testAuthor);
        Mockito.when(authorRepository.findById(Mockito.any())).thenReturn(optionalAuthor);
        BigDecimal allBookPrice = bookService.getAllBookPrice(3l);
        BigDecimal testSum = new BigDecimal(0);
        for (Book book : bookSet) {
            testSum = testSum.add(book.getPrice());
        }
        Assertions.assertEquals(testSum, allBookPrice);
    }
}