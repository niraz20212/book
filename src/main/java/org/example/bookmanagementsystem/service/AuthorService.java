package org.example.bookmanagementsystem.service;

import org.example.bookmanagementsystem.entity.Author;


import java.util.List;


public interface AuthorService {
    Author save(Author author);

    void deleteById(int id);

    List<Author> getAllAuthorList();
}
