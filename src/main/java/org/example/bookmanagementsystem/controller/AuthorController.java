package org.example.bookmanagementsystem.controller;

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
    public Author save(@RequestBody Author author){
       return authorService.save(author);
   }
   @DeleteMapping("/delete/{id}")
   public void deleteById(@PathVariable int id){
        authorService.deleteById(id);
   }
   @PutMapping("/update")
    public Author updateAuthor(Author author){
      return  authorService.updateAuthor(author);

    }
    @GetMapping("/getAll")
    public List<Author> getAll(){
        return authorService.getAllAuthorList();
    }
}
