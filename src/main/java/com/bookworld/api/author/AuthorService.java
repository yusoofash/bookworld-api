package com.bookworld.api.author;

import java.util.List;

public interface AuthorService {
    Author createAuthor(Author author);

    Author updateAuthor(Long authorId, Author author);

    List<Author> getAllAuthors();

    void deleteAuthor(Long authorId);
}
