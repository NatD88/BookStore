package ru.bookstore.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.bookstore.entity.Author;
import ru.bookstore.repository.AuthorRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;

    public List<Author> getAuthors() {
        return authorRepository.findAll();
    }

}
