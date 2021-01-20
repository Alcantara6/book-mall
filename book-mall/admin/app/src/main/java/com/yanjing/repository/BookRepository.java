package com.yanjing.repository;

import com.yanjing.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author yanjing
 * @date 2021-01-20
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    // TODO: 分页
    List<Book> findAllByName(String name);
    List<Book> findAllByAuthor(String author);

    Optional<Book> findByIsbn(String isbn);
}
