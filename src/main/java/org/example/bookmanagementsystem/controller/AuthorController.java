package org.example.bookmanagementsystem.controller;

import jakarta.validation.Valid;
import org.example.bookmanagementsystem.dto.request.AuthorDto;
import org.example.bookmanagementsystem.entity.Author;
import org.example.bookmanagementsystem.service.AuthorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {
    private AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping("/save")
    public Author save(@Valid @RequestBody AuthorDto authorDto) {
        return authorService.save(authorDto);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable int id) {
        authorService.deleteById(id);
    }



    @GetMapping("/getAll")
    public List<Author> getAll() {
        return authorService.getAllAuthorList();
    }
}
