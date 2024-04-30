package org.example.bookmanagementsystem.serviceimpl;

import org.example.bookmanagementsystem.entity.Author;
import org.example.bookmanagementsystem.entity.Book;
import org.example.bookmanagementsystem.exceptionhandler.CustomException;
import org.example.bookmanagementsystem.repository.AuthorRepository;
import org.example.bookmanagementsystem.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }


    @Override
    public Author save(Author author) {
        Author authorOptional = authorRepository.findByName(author.getName());
        if (authorOptional!=null) {
            throw new CustomException("The author already exists");
        } else {

            return authorRepository.save(author);
        }

    }

    @Override
    public void deleteById(int id) {
        authorRepository.deleteById(id);
    }


    @Override
    public List<Author> getAllAuthorList() {

        return authorRepository.findAll();
    }


}
