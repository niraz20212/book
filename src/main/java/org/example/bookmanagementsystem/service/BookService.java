package org.example.bookmanagementsystem.service;

import org.example.bookmanagementsystem.dto.request.BookRequest;
import org.example.bookmanagementsystem.dto.response.BookResponse;
import org.example.bookmanagementsystem.entity.BookTransaction;

import java.util.List;

public interface BookService {
    Boolean addBook(BookRequest bookRequest);

    void deleteBook(int id);

    List<BookResponse> getAllBooks();

    void rentBookForMember(String bookName, String memberName);


    List<BookTransaction> getListOfRentedBooks();

    void returnBookForMember(String bookName, String memberName);

}
