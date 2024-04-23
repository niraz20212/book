package org.example.bookmanagementsystem.serviceimpl;

import jakarta.transaction.Transactional;
import org.example.bookmanagementsystem.entity.Book;
import org.example.bookmanagementsystem.entity.BookTransaction;
import org.example.bookmanagementsystem.entity.Member;
import org.example.bookmanagementsystem.enums.RentStatus;
import org.example.bookmanagementsystem.repository.BookRepository;
import org.example.bookmanagementsystem.repository.BookTransactionRepository;
import org.example.bookmanagementsystem.repository.MemberRepository;
import org.example.bookmanagementsystem.service.BookService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;
    private final BookTransactionRepository bookTransactionRepository;

    public BookServiceImpl(BookRepository bookRepository, MemberRepository memberRepository, BookTransactionRepository bookTransactionRepository) {
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
        this.bookTransactionRepository = bookTransactionRepository;
    }

    @Override
    @Transactional
    public Book addBook(Book book) {
        Optional<Book> bookOptional = bookRepository.findById(book.getId());
        if (bookOptional.isPresent()) {
            throw new RuntimeException("Book already exists");
        } else {
            return bookRepository.save(book);
        }

    }

    @Override
    public Book updateBook(Book book) {
        Optional<Book> bookOptional = bookRepository.findById(book.getId());
        if (bookOptional.isPresent()) {
            Book bookToUpdate = bookOptional.get();
            bookToUpdate.setName(book.getName());
            bookToUpdate.setRating(book.getRating());
            bookToUpdate.setIsbn(book.getIsbn());
            bookToUpdate.setStockCount(book.getStockCount());
            bookToUpdate.setNoOfPages(book.getNoOfPages());
            bookToUpdate.setPublishDate(book.getPublishDate());
            bookToUpdate.setPhoto(book.getPhoto());
            return bookRepository.save(bookToUpdate);
        } else {
            throw new RuntimeException("Book not found");
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
                new IllegalArgumentException("Invalid member id"))
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


}
