package com.bookworld.api.genre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/genre")
public class GenreController {
    @Autowired
    private GenreService genreService;

    @PostMapping
    public ResponseEntity<Genre> createGenre(@RequestBody Genre genre) {
        return ResponseEntity.ok(genreService.createGenre(genre));
    }

    @PutMapping("/{genreId}")
    public ResponseEntity<Genre> updateGenre(@PathVariable Long genreId, @RequestBody Genre genre) {
        return ResponseEntity.ok(genreService.updateGenre(genreId, genre));
    }

    @DeleteMapping("/{genreId}")
    public HttpStatus deleteGenre(@PathVariable Long genreId) {
        genreService.deleteGenre(genreId);
        return HttpStatus.OK;
    }

    @GetMapping
    public ResponseEntity<List<Genre>> getGenres() {
        return ResponseEntity.ok(genreService.getAllGenres());
    }
}
