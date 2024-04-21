package org.example.bookmanagementsystem.service;

import org.example.bookmanagementsystem.entity.Book;
import java.util.List;

public interface BookService {
    Book addBook(Book book);
    Book updateBook(Book book);
    void deleteBook(int id);
    List<Book> getAllBooks();

}
