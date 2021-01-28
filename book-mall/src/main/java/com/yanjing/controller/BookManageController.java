package com.yanjing.controller;

import com.yanjing.entity.Book;
import com.yanjing.service.BookService;
import com.yanjing.util.response.Response;
import com.yanjing.util.response.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;

/**
 * @author yanjing
 * @date 2021-01-23
 */
@RestController
@RequestMapping("admin")
public class BookManageController {
    @Autowired
    private BookService bookService;

    @GetMapping("/book/status/{id}")
    public Response<Book> toggleBookStatus(@PathVariable("id") @Min(1) Integer id) {
        Book book = bookService.findById(id).orElse(null);
        if (null != book) {
            bookService.toggleStatus(book);
            return ResponseUtils.success(book);
        }
        return ResponseUtils.notFound("没有找到id为" + id + "的图书！");
    }
}
