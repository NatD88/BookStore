package ru.bookStore.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.bookStore.dto.BookDTO;
import ru.bookStore.entity.Author;
import ru.bookStore.entity.Book;
import ru.bookStore.repository.AuthorRepository;
import ru.bookStore.repository.BookRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public Set<Book> getBooksByAuthor(Long author_Id) {
        Optional<Author> authorOptional = authorRepository.findById(author_Id);
        Author author = null;
        if (authorOptional.isPresent()) {
            author = authorOptional.get();
        } else {
            throw new RuntimeException("no such author id!");
        }
        return author.getBooks();
    }

    public BookDTO getBookInfo(Long book_id) {
        Book book = bookRepository.findById(book_id).orElseThrow();
        BookDTO bookDTO = new BookDTO(book, book.getAuthors());
        return bookDTO;
    }

    public BigDecimal getAllBookPrice(Long authorId) {
        Set<Book> books = getBooksByAuthor(authorId);
        BigDecimal sum = new BigDecimal(0);
        for(Book book: books) {
            sum = sum.add(book.getPrice());
        }
        return sum;
    }

}
