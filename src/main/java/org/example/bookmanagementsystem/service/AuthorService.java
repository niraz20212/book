package org.example.bookmanagementsystem.service;

import org.example.bookmanagementsystem.entity.Author;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuthorService {
    Author save(Author author);

    void deleteById(int id);

    Author updateAuthor(Author author);

    List<Author> getAllAuthorList();
}
