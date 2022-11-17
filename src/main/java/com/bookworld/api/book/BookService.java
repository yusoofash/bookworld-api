package com.bookworld.api.book;

import java.util.List;
import java.util.Set;

public interface BookService {
    Book createBook(BookPostDto bookPostDto);

    Book updateBook(Long bookId, BookPostDto bookPostDto);

    List<Book> getAllBooks();

    List<Book> getBooksByGenres(Set<Long> genreIds);

    void deleteBook(Long bookId);
}
