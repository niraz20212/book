package org.example.bookmanagementsystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
            flags = Pattern.Flag.CASE_INSENSITIVE,message = "Please Provide Valid Email")
    private String email;
    @Pattern(regexp = "\\S.*\\S", message = "Input must not start or end with whitespace")
    private String name;
    @Pattern(regexp = "\\d{10}",message = "Please Provide Valid Number")
    @Column(unique = true, nullable = false)
    private String mobileNumber;
    private String address;



}
