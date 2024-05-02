package org.example.bookmanagementsystem.service;

import org.example.bookmanagementsystem.dto.request.AuthorDto;
import org.example.bookmanagementsystem.entity.Author;


import java.util.List;


public interface AuthorService {
    Author save(AuthorDto authorDto);

    void deleteById(int id);

    List<Author> getAllAuthorList();
}
