package com.inversion.spring.book.controller;

import com.inversion.spring.base.exception.ResourceNotFoundException;
import com.inversion.spring.book.entity.BookEntity;
import com.inversion.spring.book.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ArrayList;
@RestController
public class BookApiController {

    private final BookService bookService;

    public BookApiController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/")
    public String ok(){
        return "ok";

    }

    @GetMapping("/api/v1/book")
    public List<BookEntity> getBooks(){
        return bookService.getBooks();

    }
    @GetMapping("/api/v1/book/{id}")
    public BookEntity getBookById(@PathVariable Integer id){
        return bookService.byId(id).orElseThrow(ResourceNotFoundException::new);

    }
    @PostMapping("/api/v1/book")
    public BookEntity create(@RequestBody BookEntity request){
        return bookService.create(request.getTitle(), request.getDescription());
    }
    @PutMapping("/api/v1/book/{id}")
    public BookEntity update(@PathVariable Integer id, @RequestBody BookEntity request){
        return bookService.edit(request).orElseThrow(ResourceNotFoundException::new);
    }
    @DeleteMapping("/api/v1/book/{id}")
    public BookEntity delete(@PathVariable("id") Integer id){
        return bookService.delete(id).orElseThrow(ResourceNotFoundException::new);
    }
}
