package com.belanda.jpa.controllers;

import com.belanda.jpa.dto.BookRecordDTO;
import com.belanda.jpa.models.bookModel;
import com.belanda.jpa.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/bookstore/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<bookModel>> getAllBooks(){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getAllBooks());
    }

    @PostMapping
    public ResponseEntity<bookModel> saveBook(@RequestBody BookRecordDTO bookRecordDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.saveBook(bookRecordDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable UUID id){
        bookService.deleteBook(id);
        return ResponseEntity.status(HttpStatus.OK).body("Book delete OK");
    }
}
