package com.bookworld.api.book;

import com.bookworld.api.author.Author;
import com.bookworld.api.author.AuthorRepository;
import com.bookworld.api.genre.Genre;
import com.bookworld.api.genre.GenreRepository;
import com.bookworld.api.publisher.Publisher;
import com.bookworld.api.publisher.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book createBook(BookPostDto bookPostDto) {
        List<Genre> genres = genreRepository.findAllById(bookPostDto.getGenreIds().stream().toList());
        Optional<Author> author = authorRepository.findById(bookPostDto.getAuthorId());
        Optional<Publisher> publisher = publisherRepository.findById(bookPostDto.getPublisherId());

        if (author.isEmpty()) {
            throw new RuntimeException("Author is required");
        }
        if (publisher.isEmpty()) {
            throw new RuntimeException("Publisher is required");
        }

        Book book = new Book();
        book.setTitle(bookPostDto.getTitle());
        book.setPublicationDate(bookPostDto.getPublicationDate());
        book.setPrice(bookPostDto.getPrice());
        book.setQuantity(bookPostDto.getQuantity());
        book.setAuthor(author.get());
        book.setPublisher(publisher.get());
        book.setGenres(genres);

        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(Long bookId, BookPostDto bookPostDto) {
        List<Genre> genres = genreRepository.findAllById(bookPostDto.getGenreIds().stream().toList());
        Optional<Author> author = authorRepository.findById(bookPostDto.getAuthorId());
        Optional<Publisher> publisher = publisherRepository.findById(bookPostDto.getPublisherId());

        if (author.isEmpty()) {
            throw new RuntimeException("Author is required");
        }
        if (publisher.isEmpty()) {
            throw new RuntimeException("Publisher is required");
        }

        Optional<Book> bookFound = bookRepository.findById(bookId);

        if (bookFound.isPresent()) {
            Book book = bookFound.get();

            book.setTitle(bookPostDto.getTitle());
            book.setPublicationDate(bookPostDto.getPublicationDate());
            book.setPrice(bookPostDto.getPrice());
            book.setQuantity(bookPostDto.getQuantity());
            book.setAuthor(author.get());
            book.setPublisher(publisher.get());
            book.setGenres(genres);

            return book;
        }

        return null;
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> getBooksByGenres(Set<Long> genreIds) {
        return bookRepository.findByGenreIds(genreIds);
    }

    @Override
    public void deleteBook(Long bookId) {
        bookRepository.deleteById(bookId);
    }
}
