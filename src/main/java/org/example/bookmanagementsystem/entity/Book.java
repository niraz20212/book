package org.example.bookmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "book_tbl")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private Integer noOfPages;
    private int isbn;
    private Double rating;
    private int stockCount;
    private Date publishDate;
    private String photo;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @ManyToMany(mappedBy = "book")
    private Set<Author> authors = new HashSet<>();
    @OneToMany(mappedBy = "book")
    private List<BookTransaction> bookTransactions;
}
