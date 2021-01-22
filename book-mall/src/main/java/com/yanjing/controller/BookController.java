package com.yanjing.controller;

import com.yanjing.entity.Book;
import com.yanjing.exception.BookAddFailException;
import com.yanjing.exception.BookNotFoundException;
import com.yanjing.service.BookService;
import com.yanjing.util.response.Response;
import com.yanjing.util.response.ResponseStatus;
import com.yanjing.util.response.ResponseUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

/**
 * @author yanjing
 * @date 2021-01-20
 */
@RestController
@RequestMapping("api")
public class BookController {
    @Autowired
    private BookService bookService;

    private final String PAGE_SIZE = "100";

    @GetMapping("/books")
    public Response<Page<Book>> findAllBooksByPage(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = PAGE_SIZE) Integer pageSize
    ) {
        Page<Book> books = bookService.findAllByPage(pageNo, pageSize);
        return ResponseUtils.success(books);
    }

    @GetMapping("/books/{id}")
    public Response<Book> getBookById(@PathVariable("id") @Min(1) Integer id) {
        Book book = bookService.findById(id).orElseThrow(() -> new BookNotFoundException("没有找到id为" + id + "的图书！"));
        return ResponseUtils.success(book);
    }

    @GetMapping("/findByName")
    public Response<Page<Book>> findBookByName(
            @Valid @RequestParam("name") String name,
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = PAGE_SIZE) Integer pageSize
    ) {
        Page<Book> books = bookService.findAllByName(name, pageNo, pageSize);
        return ResponseUtils.success(books);
    }

    @GetMapping("/findByIsbn")
    public Response<Page<Book>> findBookByIsbn(
            @Valid @RequestParam("isbn") String isbn,
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = PAGE_SIZE) Integer pageSize
    ) {
        Page<Book> books = bookService.findAllByIsbn(isbn, pageNo, pageSize);
        return ResponseUtils.success(books);
    }

    @PostMapping("/books")
    public Response<Book> addBook(@Valid @RequestBody Book book) {
        Book addedBook = bookService.save(book).orElse(null);
        if (null == addedBook) {
            return ResponseUtils.fail(ResponseStatus.BAD_REQUEST, "图书添加失败");
        }
        return ResponseUtils.success(addedBook);
    }

    @PutMapping("/books/{id}")
    public Response<Book> updateBook(@PathVariable @Min(1) Integer id, @Valid @RequestBody Book newBook) {
        Book book = bookService.findById(id).orElseThrow(() -> new BookNotFoundException("没有找到id为" + id + "的图书！"));
        if (null != book) {
            BeanUtils.copyProperties(newBook, book);
            book.setId(id);
            Book updatedBook = bookService.save(book).orElseThrow(() -> new BookAddFailException("更新图书失败"));
            if (null != updatedBook) {
                return ResponseUtils.success(book);
            }
            return ResponseUtils.fail(ResponseStatus.BAD_REQUEST, "更新图书失败");
        }
        return ResponseUtils.fail(ResponseStatus.NOT_FOUND, "没有找到id为" + id + "的图书！");
    }

    @DeleteMapping("books/{id}")
    public Response removeBook(@PathVariable @Min(1) Integer id) {
        Book book = bookService.findById(id).orElseThrow(() -> new BookNotFoundException("没有找到id为" + id + "的图书！"));
        bookService.removeById(book.getId());
        return ResponseUtils.success("id为" + id + "的图书已被移除");
    }
}