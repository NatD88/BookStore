package ru.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bookstore.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
