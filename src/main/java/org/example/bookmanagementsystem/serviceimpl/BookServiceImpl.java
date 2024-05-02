package org.example.bookmanagementsystem.serviceimpl;

import jakarta.transaction.Transactional;
import org.example.bookmanagementsystem.dto.request.BookRequest;
import org.example.bookmanagementsystem.dto.response.BookResponse;
import org.example.bookmanagementsystem.entity.*;
import org.example.bookmanagementsystem.enums.RentStatus;
import org.example.bookmanagementsystem.exceptionhandler.CustomException;
import org.example.bookmanagementsystem.repository.*;
import org.example.bookmanagementsystem.service.BookService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;
    private final BookTransactionRepository     bookTransactionRepository;
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
    public Boolean addBook(BookRequest bookRequest) {
        Book bookOptional = bookRepository.findByName(bookRequest.getName());
        if (bookOptional != null) {
            throw new CustomException("Book already exists");
        } else {
            Book book = new Book();
            book.setName(bookRequest.getName());
            book.setCategory(Category.builder().id(bookRequest.getCategoryId()).build());
            book.setStockCount(bookRequest.getStockCount());
            book.setIsbn(bookRequest.getIsbn());
            book.setRating(bookRequest.getRating());
            book.setNoOfPages(bookRequest.getNoOfPages());
            book.setPhoto(bookRequest.getPhoto());
            book.setPublishDate(bookRequest.getPublishDate());
            List<Author> authors = bookRequest.getAuthorId().stream().map(authorID -> Author.builder().id(authorID).build()
            ).toList();
            book.setAuthor(authors);
            bookRepository.save(book);
            return true;
        }

    }


    @Override
    public void deleteBook(int id) {
        bookRepository.deleteById(id);

    }

    @Override
    public List<BookResponse> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        List<BookResponse> bookResponses = books.stream().map(book -> BookResponse.builder()
                .id(book.getId())
                .name(book.getName())
                .isbn(book.getIsbn())
                .stockCount(book.getStockCount())
                .photo(book.getPhoto())
                .noOfPages(book.getNoOfPages())
                .publishDate(book.getPublishDate())
                .rating(book.getRating())
                .build()).toList();
        return bookResponses;

    }

    @Override
    @Transactional
    public void rentBookForMember(String bookName, String memberName) {
        Book book=bookRepository.findByName(bookName);
        if(book.getStockCount()==null){
            throw new CustomException("Book does not exist");
        }
        Member member=memberRepository.findByName(memberName);
        if(member==null){
            throw new CustomException("Member does not exist");
        }


       Optional<BookTransaction> bookTransaction=bookTransactionRepository.findById(member.getId());
        if(!bookTransaction.isPresent() || bookTransaction.get().getActive()==false){
            BookTransaction bookTransactionNew = new BookTransaction();
            bookTransactionNew.setMember(Member.builder().id(member.getId()).build());
            bookTransactionNew.setBook(Book.builder().id(book.getId()).build());
            bookTransactionNew.setRentStatus(RentStatus.RENTED);
            bookTransactionNew.setFromDate(new Date());

            LocalDate toDate = LocalDate.now().plusDays(10);

// Convert LocalDate to LocalDateTime at start of day
            LocalDateTime startOfDay = toDate.atStartOfDay();

// Convert LocalDateTime to Date
            Date toDateAsDate = java.util.Date.from(startOfDay.atZone(java.time.ZoneId.systemDefault()).toInstant());

// Set the toDate in your object
            bookTransactionNew.setToDate(toDateAsDate);
            bookTransactionNew.setActive(true);
            book.setStockCount(book.getStockCount() - 1);
            bookTransactionRepository.save(bookTransactionNew);
        }else {

        }


    }



    @Override
    public List<BookTransaction> getListOfRentedBooks() {

        return bookTransactionRepository.findAllByActiveTrue();
    }

    @Override
    public void returnBookForMember(String bookName, String memberName) {
        Book book=bookRepository.findByName(bookName);

        Member member=memberRepository.findByName(memberName);

        Optional<BookTransaction> bookTransaction=bookTransactionRepository.findById(member.getId());
        if(bookTransaction.isPresent()){
            BookTransaction bookTransactionNew = new BookTransaction();
            bookTransactionNew.setMember(Member.builder().id(member.getId()).build());
            bookTransactionNew.setBook(Book.builder().id(book.getId()).build());
            bookTransactionNew.setRentStatus(RentStatus.RETURNED);
            bookTransactionNew.setToDate(new Date());
            bookTransactionNew.setActive(true);
            book.setStockCount(book.getStockCount() + 1);
            bookTransactionRepository.save(bookTransactionNew);
        }


    }



}
