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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;
    private final BookTransactionRepository transactionRepository;

    public BookServiceImpl(BookRepository bookRepository, MemberRepository memberRepository, BookTransactionRepository transactionRepository) {
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
        this.transactionRepository = transactionRepository;
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
        List<Book> books = new ArrayList<Book>();
        bookRepository.findAll().forEach(book -> books.add(book));
        return books;
    }

    @Override
    @Transactional
    public void rentBookForMember(Integer memberId, Integer bookId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() ->
                new IllegalArgumentException("Invalid member id"));
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new IllegalArgumentException("Invalid book id"));
        if (member.getBookTransactions().stream().anyMatch(transaction -> transaction.getActive())) {
            throw new IllegalArgumentException("Member already Rented a Book");
        }
        if (book.getStockCount() <= 0) {
            throw new IllegalArgumentException("Book is out of Stock    ");
        }
        BookTransaction bookTransaction = new BookTransaction();
        bookTransaction.setFromDate(new Date());
        bookTransaction.setRentStatus(RentStatus.RENTED);
        bookTransaction.setActive(true);
        bookTransaction.setClosed(false);
        bookTransaction.setBook(book);
        bookTransaction.setMember(member);
        book.getBookTransactions().add(bookTransaction);
        member.getBookTransactions().add(bookTransaction);
        book.setStockCount(book.getStockCount() - 1);
        memberRepository.save(member);


    }

    @Override
    @Transactional
    public void returnBookForMember(Integer memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("Member not found"));
        BookTransaction bookTransaction = member.getBookTransactions().stream().filter(t -> t.getActive()).findFirst().get();
        if (bookTransaction == null) {
            throw new IllegalArgumentException("Book does not have Rented a Book");
        }
        bookTransaction.setToDate(new Date());
        bookTransaction.setActive(false);
        bookTransaction.setClosed(true);
        bookTransaction.setRentStatus(RentStatus.RETURN);
        Book book = bookTransaction.getBook();
        book.setStockCount(book.getStockCount() + 1);
        memberRepository.save(member);

    }

}
