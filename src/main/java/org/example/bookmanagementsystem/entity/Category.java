package org.example.bookmanagementsystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;





@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Pattern(regexp = "\\S.*\\S", message = "Input must not start or end with whitespace")
    private String name;
    private String description;





}
