package org.example.bookmanagementsystem.service;

import org.example.bookmanagementsystem.entity.Book;
import org.example.bookmanagementsystem.entity.BookTransaction;

import java.util.List;

public interface BookService {
    Book addBook(Book book);

    void deleteBook(int id);

    List<Book> getAllBooks();

    void rentBookForMember(Integer memberId, Integer bookId);


    void returnBookFormMember(Integer memberId, Integer bookId);
    List<BookTransaction> getListofRentedBooks();

}
