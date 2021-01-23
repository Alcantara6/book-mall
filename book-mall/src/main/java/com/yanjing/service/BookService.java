package com.yanjing.service;

import com.yanjing.entity.Book;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface BookService {
    Page<Book> findAllByPage(Integer pageNo, Integer pageSize);
    Optional<Book> findById(Integer id);
    Page<Book> findAllByName(String name, Integer pageNo, Integer pageSize);
    Page<Book> findAllByIsbn(String isbn, Integer pageNo, Integer pageSize);
    Optional<Book> findByIsbn(String isbn);

    Optional<Book> save(Book book);

    void toggleStatus(Book book);

    void removeById(Integer id);
}
