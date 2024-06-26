package org.example.bookmanagementsystem.controller;

import org.example.bookmanagementsystem.entity.Book;
import org.example.bookmanagementsystem.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;


    @PostMapping("/save")
    public Book addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    @DeleteMapping("delete/{id}")
    public void deleteBookById(@PathVariable int id) {
        bookService.deleteBook(id);
    }

    @GetMapping("/find-all")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();

    }

    @PutMapping("/update")
    public void updateBook(@RequestBody Book book) {
        bookService.updateBook(book);
    }

    @PostMapping("/{bookId}/rent/{memberId}")
    public ResponseEntity<?> rentBookForMember(@PathVariable int bookId, @PathVariable int memberId) {
        bookService.rentBookForMember(memberId, bookId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{bookId}/return/{memberId}")
    public ResponseEntity<?> returnBookForMember(@PathVariable int bookId, @PathVariable int memberId) {
        bookService.returnBookForMember(memberId);
        return ResponseEntity.ok().build();
    }


}
