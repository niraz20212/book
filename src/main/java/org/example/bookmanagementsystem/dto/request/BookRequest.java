package org.example.bookmanagementsystem.dto.request;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.bookmanagementsystem.entity.Category;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
public class BookRequest {

    private Integer id;
    private String name;
    private Integer noOfPages;
    private Integer isbn;
    private Double rating;
    private Integer stockCount;
    private Date publishDate;
    private String photo;
    private Integer categoryId;
    private List<Integer> authorId;

}
