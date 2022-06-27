package ru.bookStore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bookStore.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
