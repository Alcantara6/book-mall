package com.yanjing.service;

import com.yanjing.entity.Book;
import com.yanjing.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> findAllBooks() {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        return bookRepository.findAll(sort);
    };

    @Override
    public Optional<Book> findById(Integer id) {
        return bookRepository.findById(id);
    }

    @Override
    public Optional<Book> findByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    @Override
    public Book save(Book book) {
        return bookRepository.saveAndFlush(book);
    }

    @Override
    public void removeById(Integer id) {
        bookRepository.deleteById(id);
    }
}
