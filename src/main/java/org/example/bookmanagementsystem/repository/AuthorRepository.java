package org.example.bookmanagementsystem.repository;

import org.example.bookmanagementsystem.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
    Author findByName(String name);

}
