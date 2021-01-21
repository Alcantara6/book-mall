package com.yanjing.service;

import com.yanjing.entity.Book;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface BookService {
    Page<Book> findAllByPage(Integer pageNo);
    Optional<Book> findById(Integer id);
    Page<Book> findAllByName(String name, Integer pageNo);
    Page<Book> findAllByIsbn(String isbn, Integer pageNo);
    Optional<Book> findByIsbn(String isbn);

    Optional<Book> save(Book book);

    void removeById(Integer id);
}
