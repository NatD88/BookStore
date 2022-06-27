package ru.bookStore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bookStore.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
