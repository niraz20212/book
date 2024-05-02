package org.example.bookmanagementsystem.dto.response;

import lombok.Builder;
import org.example.bookmanagementsystem.entity.Category;

import java.util.Date;
@Builder
public class BookResponse {
    private Integer id;
    private String name;
    private Integer noOfPages;
    private Integer isbn;
    private Double rating;
    private Integer stockCount;
    private Date publishDate;
    private String photo;
    private Integer categoryId;

}
