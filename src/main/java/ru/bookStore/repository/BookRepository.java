package ru.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import ru.bookstore.entity.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("from Book where price > 750")
    List<Book> myQuery();



}
