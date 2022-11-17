package com.bookworld.api.genre;

import com.bookworld.api.genre.Genre;

import java.util.List;

public interface GenreService {
    Genre createGenre(Genre genre);

    Genre updateGenre(Long genreId, Genre genre);

    List<Genre> getAllGenres();

    void deleteGenre(Long genreId);
}
