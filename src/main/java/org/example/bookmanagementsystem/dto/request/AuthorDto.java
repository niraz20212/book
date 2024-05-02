package org.example.bookmanagementsystem.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorDto {
    private Integer id;
    private String name;
    private String email;
    private String mobileNumber;

}
