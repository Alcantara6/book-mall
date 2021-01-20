package com.yanjing.controller;

import com.yanjing.entity.Book;
import com.yanjing.exception.BookNotFoundException;
import com.yanjing.service.BookService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("api")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public List<Book> findAllBooks() {
        return bookService.findAllBooks();
    }

    @GetMapping("/books/{id}")
    public Book findBookById(@PathVariable("id") @Min(1) Integer id) {
        return bookService.findById(id).orElseThrow(() -> new BookNotFoundException("没有找到id为" + id + "的图书！"));
    }

    @GetMapping("/book")
    public Book findBookByIsbn(@Valid @RequestParam("isbn") String isbn) {
        return bookService.findByIsbn(isbn).orElseThrow(() -> new BookNotFoundException("国际标准书号为" + isbn + "的图书！"));
    }

    @PostMapping("/books")
    public Book addBook(@Valid @RequestBody Book book) {
        return bookService.save(book);
    }

    @PutMapping("/books/{id}")
    public Book updateBook(@PathVariable @Min(1) Integer id, @Valid @RequestBody Book newBook) {
        Book book = bookService.findById(id).orElseThrow(() -> new BookNotFoundException("没有找到id为" + id + "的图书！"));
        if (null != book) {
            BeanUtils.copyProperties(newBook, book);
            book.setId(id);
            bookService.save(book);
        }
        return book;
    }

    @DeleteMapping("books/{id}")
    public String removeBook(@PathVariable @Min(1) Integer id) {
        Book book = bookService.findById(id).orElseThrow(() -> new BookNotFoundException("没有找到id为" + id + "的图书！"));
        bookService.removeById(book.getId());
        return "id为" + id + "的图书已被移除";
    }
}
