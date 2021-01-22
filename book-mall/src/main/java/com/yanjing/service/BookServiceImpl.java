package com.yanjing.service;

import com.yanjing.entity.Book;
import com.yanjing.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.Optional;

/**
 * @author yanjing
 * @date 2021-01-21
 */
@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    private final Integer PAGE_SIZE = 2;

    @Override
    public Page<Book> findAllByPage(Integer pageNo) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(pageNo, PAGE_SIZE, sort);
        return bookRepository.findAll(pageable);
    }

    @Override
    public Optional<Book> findById(Integer id) {
        return bookRepository.findById(id);
    }

    @Override
    public Optional<Book> findByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    @Override
    public Page<Book> findAllByName(String name, Integer pageNo) {
        Sort sort = Sort.by(Sort.Direction.DESC, "name");
        Pageable pageable = PageRequest.of(pageNo, PAGE_SIZE, sort);
        return bookRepository.findAllByNameLike('%' + name + '%', pageable);
    }

    @Override
    public Page<Book> findAllByIsbn(String isbn, Integer pageNo) {
        Sort sort = Sort.by(Sort.Direction.DESC, "isbn");
        Pageable pageable = PageRequest.of(pageNo, PAGE_SIZE, sort);
        return bookRepository.findAllByIsbn(isbn, pageable);
    }

    @Override
    public Optional<Book> save(Book book) {
        if (!findByIsbn(book.getIsbn()).isPresent()) {
            return Optional.of(bookRepository.saveAndFlush(book));
        }
        return Optional.empty();
    }

    @Override
    public void removeById(Integer id) {
        bookRepository.deleteById(id);
    }
}
