package org.example.bookmanagementsystem.serviceimpl;

import jakarta.transaction.Transactional;
import org.example.bookmanagementsystem.entity.Book;
import org.example.bookmanagementsystem.repository.BookRepository;
import org.example.bookmanagementsystem.service.BookService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    @Transactional
    public Book addBook(Book book) {
        Optional<Book> bookOptional = bookRepository.findById(book.getId());
        if (bookOptional.isPresent()){
            throw new RuntimeException("Book already exists");
        }else {
            return bookRepository.save(book);
        }

    }

    @Override
    public Book updateBook(Book book) {
        Optional<Book> bookOptional=bookRepository.findById(book.getId());
        if (bookOptional.isPresent()){
            Book bookToUpdate=bookOptional.get();
            bookToUpdate.setName(book.getName());
            bookToUpdate.setRating(book.getRating());
            bookToUpdate.setIsbn(book.getIsbn());
            bookToUpdate.setStockCount(book.getStockCount());
            bookToUpdate.setNoOfPages(book.getNoOfPages());
            bookToUpdate.setPublishDate(book.getPublishDate());
            bookToUpdate.setPhoto(book.getPhoto());
            return bookRepository.save(bookToUpdate);
        }else {
            throw new RuntimeException("Book not found");
        }


    }

    @Override
    public void deleteBook(int id) {
      bookRepository.deleteById(id);

    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<Book>();
        bookRepository.findAll().forEach(book -> books.add(book));
        return books;
    }

}
