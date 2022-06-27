package ru.bookStore.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.bookStore.entity.Author;
import ru.bookStore.repository.AuthorRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;

    public List<Author> getAuthors() {
        return authorRepository.findAll();
    }

   }
