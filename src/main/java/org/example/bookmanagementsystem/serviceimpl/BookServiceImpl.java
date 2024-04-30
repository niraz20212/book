package org.example.bookmanagementsystem.serviceimpl;

import jakarta.transaction.Transactional;
import org.example.bookmanagementsystem.entity.*;
import org.example.bookmanagementsystem.enums.RentStatus;
import org.example.bookmanagementsystem.exceptionhandler.CustomException;
import org.example.bookmanagementsystem.repository.*;
import org.example.bookmanagementsystem.service.BookService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;
    private final BookTransactionRepository bookTransactionRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;

    public BookServiceImpl(BookRepository bookRepository, MemberRepository memberRepository, BookTransactionRepository bookTransactionRepository, AuthorRepository authorRepository, CategoryRepository categoryRepository) {
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
        this.bookTransactionRepository = bookTransactionRepository;
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional
    public Book addBook(Book book) {
        Book bookOptional = bookRepository.findByName(book.getName());
        if (bookOptional!=null) {
            throw new CustomException("Book already exists");
        } else {

            return bookRepository.save(book);
        }

    }


    @Override
    public void deleteBook(int id) {
        bookRepository.deleteById(id);

    }

    @Override
    public List<Book> getAllBooks() {

        return bookRepository.findAll();

    }

    @Override
    @Transactional
    public void rentBookForMember(Integer memberId, Integer bookId) {
        memberRepository.findById(memberId).orElseThrow(() ->
                new IllegalArgumentException("Invalid member id"));
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new IllegalArgumentException("Invalid book id"));
        if (book.getStockCount() == 0) {
            throw new RuntimeException("Book does not have any stock");
        }

        if (bookTransactionRepository.existedByMemberIdAndActiveTrue(memberId) == true) {
            BookTransaction bookTransaction = new BookTransaction();

            bookTransaction.setRentStatus(RentStatus.RENTED);
            bookTransaction.setFromDate(new Date());
            bookTransaction.setActive(false);
            book.setStockCount(book.getStockCount() - 1);
            bookTransactionRepository.save(bookTransaction);
        } else {
            throw new IllegalArgumentException("Member has already been rent");
        }


    }

    @Override
    @Transactional
    public void returnBookFormMember(Integer memberId, Integer bookId) {
        memberRepository.findById(memberId).orElseThrow(() ->
                new IllegalArgumentException("Invalid member id"));
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new IllegalArgumentException("Invalid book id"));

        if (bookTransactionRepository.existedByMemberIdAndActiveTrue(memberId) == false) {
            BookTransaction bookTransaction = new BookTransaction();
            bookTransaction.setRentStatus(RentStatus.RETURNED);
            bookTransaction.setToDate(new Date());
            bookTransaction.setActive(true);
            book.setStockCount(book.getStockCount() + 1);
            bookTransactionRepository.save(bookTransaction);
        } else {
            throw new IllegalArgumentException("Member has already been returned");
        }


    }

    @Override
    public List<BookTransaction> getListofRentedBooks() {
        return bookTransactionRepository.findAllByActiveTrue();
    }


}
