package com.yanjing.service;

import com.yanjing.entity.Book;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findAllBooks();
    Optional<Book> findById(Integer id);
    Optional<Book> findByIsbn(String isbn);

    Book save(Book book);

    void removeById(Integer id);
}
