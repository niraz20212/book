package org.example.bookmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;



@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(unique = true, nullable = false)
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


}
