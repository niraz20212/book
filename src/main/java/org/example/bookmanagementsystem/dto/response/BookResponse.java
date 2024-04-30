package org.example.bookmanagementsystem.dto.response;

import org.example.bookmanagementsystem.entity.Category;

import java.util.Date;

public class BookResponse {
    private int id;
    private String name;
    private Integer noOfPages;
    private int isbn;
    private Double rating;
    private int stockCount;
    private Date publishDate;
    private String photo;
    private Category category;

}
