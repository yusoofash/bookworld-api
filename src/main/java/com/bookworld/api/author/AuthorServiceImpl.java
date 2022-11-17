package com.bookworld.api.author;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Author updateAuthor(Long authorId, Author author) {
        Optional<Author> existingAuthor = authorRepository.findById(authorId);
        if (existingAuthor.isPresent()) {
            Author authorUpdate = existingAuthor.get();
            authorUpdate.setFirstName(author.getFirstName());
            authorUpdate.setLastName(author.getLastName());

            return authorRepository.save(authorUpdate);
        }

        return null;
    }

    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public void deleteAuthor(Long authorId) {
        authorRepository.deleteById(authorId);
    }
}
