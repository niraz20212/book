package org.example.bookmanagementsystem.serviceimpl;

import org.example.bookmanagementsystem.entity.Author;
import org.example.bookmanagementsystem.repository.AuthorRepository;
import org.example.bookmanagementsystem.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class AuthorServiceImpl implements AuthorService {
    private AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }


    @Override
    public Author save(Author author) {
        Optional<Author> authorOptional = authorRepository.findById(author.getId());
        if (authorOptional.isPresent()){
            throw new RuntimeException("Author Already Exists");
        }else {
            return authorRepository.save(author);
        }

    }

    @Override
    public void deleteById(int id) {
      authorRepository.deleteById(id);
    }

    @Override
    public Author updateAuthor(Author author) {
        Optional<Author> authorOptional = authorRepository.findById(author.getId());
        if (authorOptional.isPresent()){
                Author updateAuthor=authorOptional.get();
                updateAuthor.setName(author.getName());
                updateAuthor.setEmail(author.getEmail());
                updateAuthor.setMobileNumber(author.getMobileNumber());

       return authorRepository.save(updateAuthor); }else {
            throw new RuntimeException("Author Not Found");
        }

    }

    @Override
    public List<Author> getAllAuthorList() {

        return authorRepository.findAll();
    }


}
