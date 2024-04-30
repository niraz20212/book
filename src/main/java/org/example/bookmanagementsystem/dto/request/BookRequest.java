package org.example.bookmanagementsystem.dto.request;

import jakarta.persistence.*;
import org.example.bookmanagementsystem.entity.Category;

import java.util.Date;

public class BookRequest {

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
