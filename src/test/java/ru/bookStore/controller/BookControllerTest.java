package ru.bookstore.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.bookstore.dto.BookDTO;
import ru.bookstore.entity.Author;
import ru.bookstore.entity.Book;
import ru.bookstore.service.AuthorService;
import ru.bookstore.service.BookService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    ObjectMapper mapper = new ObjectMapper();

    @MockBean
    private AuthorService authorService;
    @MockBean
    private BookService bookService;

    private MockMvc mockMvc;

    private List<Book> bookList;
    private List<Author> authorList;
    private Set<Book> bookSet;
    private BookDTO testBookDTO;

    @BeforeEach
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();

        bookList = new ArrayList<>();
        Book book3 = new Book("Colors", new BigDecimal(700));
        Book book4 = new Book("Fish", new BigDecimal(300));
        bookList.add(book3);
        bookList.add(book4);

        authorList = new ArrayList<>();
        Author author1 = new Author("Donald");
        Author author2 = new Author("King");
        authorList.add(author1);
        authorList.add(author2);

        bookSet = new HashSet<Book>();
        Book book1 = new Book("Dreams", new BigDecimal(1500));
        Book book2 = new Book("Goals", new BigDecimal(900));
        bookSet.add(book1);
        bookSet.add(book2);

        testBookDTO = new BookDTO(book1, new HashSet<>());
    }


    @Test
    void getAllBooks() throws Exception {

        Mockito.when(bookService.getBooks()).thenReturn(bookList);

        MockHttpServletRequestBuilder mockRequest =
                MockMvcRequestBuilders.
                        get("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(bookList)));

    }

    @Test
    void getAllAuthors() throws Exception {

        Mockito.when(authorService.getAuthors()).thenReturn(authorList);

        MockHttpServletRequestBuilder mockRequest =
                MockMvcRequestBuilders.
                        get("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(authorList)));
    }

    @Test
    void getAllAuthorBooks() throws Exception {

        Mockito.when(bookService.getBooksByAuthor(Mockito.any())).thenReturn(bookSet);

        MockHttpServletRequestBuilder mockRequest =
                MockMvcRequestBuilders.
                        get("/booksByAuthor")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(bookSet)));
    }

    @Test
    void getBookInfo() throws Exception {

        Mockito.when(bookService.getBookInfo(Mockito.any())).thenReturn(testBookDTO);

        MockHttpServletRequestBuilder mockRequest =
                MockMvcRequestBuilders.
                        get("/allBookInfo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(testBookDTO)));
    }

    @Test
    void getAllBookPrice() throws Exception {

        BigDecimal testPrice = new BigDecimal(2500);

        Mockito.when(bookService.getAllBookPrice(Mockito.any())).thenReturn(testPrice);

        MockHttpServletRequestBuilder mockRequest =
                MockMvcRequestBuilders.
                        get("/allBookOfAutherPrice")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(testPrice)));

    }
}