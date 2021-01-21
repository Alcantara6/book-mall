package com.yanjing.repository;

import com.yanjing.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    Page<Book> findAllByNameLike(String name, Pageable pageable);
    Page<Book> findAllByIsbn(String isbn, Pageable pageable);
}
