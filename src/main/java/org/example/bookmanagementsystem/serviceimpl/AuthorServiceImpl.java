package org.example.bookmanagementsystem.serviceimpl;

import org.example.bookmanagementsystem.dto.request.AuthorDto;
import org.example.bookmanagementsystem.entity.Author;
import org.example.bookmanagementsystem.exceptionhandler.CustomException;
import org.example.bookmanagementsystem.repository.AuthorRepository;
import org.example.bookmanagementsystem.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }


    @Override
    public Author save(AuthorDto authorDto) {
        Author authorDtoOptional = authorRepository.findByName(authorDto.getName());
        if (authorDtoOptional !=null) {
            throw new CustomException("The author already exists");
        } else {
            Author author = new Author();
            author.setName(authorDto.getName());
            author.setMobileNumber(authorDto.getMobileNumber());
            author.setEmail(authorDto.getEmail());


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
