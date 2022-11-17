package com.bookworld.api.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody BookPostDto bookPostDto) {
        return ResponseEntity.ok(bookService.createBook((bookPostDto)));
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<Book> updateBook(@PathVariable Long bookId, @RequestBody BookPostDto bookPostDto) {
        return ResponseEntity.ok(bookService.updateBook(bookId, bookPostDto));
    }

    @DeleteMapping("/{bookId}")
    public HttpStatus deleteBook(@PathVariable Long bookId) {
        bookService.deleteBook(bookId);
        return HttpStatus.OK;
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks(
            @RequestParam(name = "genre_ids", required = false) Optional<Set<Long>> genreIds
            ) {
        if (genreIds.isPresent()) {
            return ResponseEntity.ok(bookService.getBooksByGenres(genreIds.get()));
        }
        return ResponseEntity.ok(bookService.getAllBooks());
    }

}
