package org.example.bookmanagementsystem.controller;


import org.example.bookmanagementsystem.entity.Book;

import org.example.bookmanagementsystem.repository.BookTransactionRepository;
import org.example.bookmanagementsystem.service.BookService;

import org.example.bookmanagementsystem.utils.ExcelGenerator;
import org.example.bookmanagementsystem.utils.PdfGenerator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;
    private final BookTransactionRepository bookTransactionRepository;

    public BookController(BookService bookService, BookTransactionRepository bookTransactionRepository) {
        this.bookService = bookService;
        this.bookTransactionRepository = bookTransactionRepository;
    }


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


    @PostMapping("/rent/{bookId}/{memberId}")
    public ResponseEntity<?> rentBookForMember(@PathVariable int bookId, @PathVariable int memberId) {
        bookService.rentBookForMember(bookId, memberId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/return/{bookId}/{memberId}")
    public ResponseEntity<?> returnBookForMember(@PathVariable int bookId, @PathVariable int memberId) {
        bookService.returnBookFormMember(bookId, memberId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/export-to-excel")
    public ModelAndView exportIntoExcelFile() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("list", bookService.getListofRentedBooks());
        modelAndView.setView(new ExcelGenerator());
        return modelAndView;
    }

    @GetMapping("/export-to-pdf")
    public ModelAndView exportIntoPdf() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("list", bookService.getListofRentedBooks());
        modelAndView.setView(new PdfGenerator());
        return modelAndView;
    }


}
