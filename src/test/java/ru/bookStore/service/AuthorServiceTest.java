package ru.bookstore.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import ru.bookstore.entity.Author;
import ru.bookstore.repository.AuthorRepository;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    private AuthorService authorService;
    private List<Author> testList;

    @BeforeEach
    void init() {
        authorService = new AuthorService(authorRepository);
        testList = new ArrayList<>();
        Author author1 = new Author("Donald");
        Author author2 = new Author("King");
        testList.add(author1);
        testList.add(author2);
    }

    @Test
    void getAuthors() {

        Mockito.when(authorRepository.findAll()).thenReturn(testList);
        List<Author> resList = authorService.getAuthors();
        Assertions.assertEquals(testList, resList);
    }
}