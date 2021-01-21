package com.yanjing.controller;

import com.yanjing.entity.Book;
import com.yanjing.exception.BookNotFoundException;
import com.yanjing.service.BookService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    public Page<Book> findAllBooksByPage(@RequestParam(defaultValue = "0") Integer pageNo) {
        return bookService.findAllByPage(pageNo);
    }

    @GetMapping("/books/{id}")
    public Book getBookById(@PathVariable("id") @Min(1) Integer id) {
        return bookService.findById(id).orElseThrow(() -> new BookNotFoundException("没有找到id为" + id + "的图书！"));
    }

    @GetMapping("/findByName")
    public Page<Book> findBookByName(@Valid @RequestParam("name") String name, @RequestParam(defaultValue = "0") Integer pageNo) {
        return bookService.findAllByName(name, pageNo);
    }

    @GetMapping("/findByIsbn")
    public Page<Book> findBookByIsbn(@Valid @RequestParam("isbn") String isbn, @RequestParam(defaultValue = "0") Integer pageNo) {
        return bookService.findAllByIsbn(isbn, pageNo);
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
