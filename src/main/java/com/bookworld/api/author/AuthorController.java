package com.bookworld.api.author;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping
    public ResponseEntity<Author> createAuthor(@RequestBody Author author) {
        return ResponseEntity.ok(authorService.createAuthor(author));
    }

    @PutMapping("/{authorId}")
    public ResponseEntity<Author> updateAuthor(@PathVariable Long authorId, @RequestBody Author author) {
        return ResponseEntity.ok(authorService.updateAuthor(authorId, author));
    }

    @DeleteMapping("/{authorId}")
    public HttpStatus deleteAuthor(@PathVariable Long authorId) {
        authorService.deleteAuthor(authorId);
        return HttpStatus.OK;
    }

    @GetMapping
    public ResponseEntity<List<Author>> getAuthors() {
        return ResponseEntity.ok(authorService.getAllAuthors());
    }

}
