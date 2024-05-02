package org.example.bookmanagementsystem.controller;


import org.example.bookmanagementsystem.dto.request.BookRequest;
import org.example.bookmanagementsystem.dto.response.BookResponse;

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
    public Boolean addBook(@RequestBody BookRequest bookRequest) {
        bookService.addBook(bookRequest);
        return true;
    }

    @DeleteMapping("delete/{id}")
    public void deleteBookById(@PathVariable int id) {
        bookService.deleteBook(id);
    }

    @GetMapping("/find-all")
    public List<BookResponse> getAllBooks() {
        return bookService.getAllBooks();

    }


    @PostMapping("/rent/{bookName}/{memberName}")
    public ResponseEntity<?> rentBookForMember(@PathVariable String bookName, @PathVariable String memberName) {
        bookService.rentBookForMember(bookName, memberName);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/return/{bookName}/{memberName}")
    public ResponseEntity<?> returnBookForMember(@PathVariable String bookName, @PathVariable String memberName) {
        bookService.returnBookForMember(bookName, memberName);
        return ResponseEntity.ok().build();
    }



    @GetMapping("/export-to-excel")
    public ModelAndView exportIntoExcelFile() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("list", bookService.getListOfRentedBooks());
        modelAndView.setView(new ExcelGenerator());
        return modelAndView;
    }

    @GetMapping("/export-to-pdf")
    public ModelAndView exportIntoPdf() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("list", bookService.getListOfRentedBooks());
        modelAndView.setView(new PdfGenerator());
        return modelAndView;
    }


}
