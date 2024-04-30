package org.example.bookmanagementsystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(unique = true, nullable = false)
    @Size(min = 3, max = 20,message = "Please Enter Character equal or more than three")
    private String name;
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
            flags = Pattern.Flag.CASE_INSENSITIVE,message = "Please Provide Valid Email")
    private String email;
    @Pattern(regexp = "\\d{10}",message = "Please Provide Valid Number")
    @Column(unique = true, nullable = false)
    private String mobileNumber;
    @ManyToMany
    @JoinTable(
            name = "author_book",
            joinColumns = {@JoinColumn(name = "author_id")},
            inverseJoinColumns = {@JoinColumn(name = "book_id")}
    )

    private List<Book> books;
}
