package com.bookworld.api.genre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService {

    @Autowired
    private GenreRepository genreRepository;

    @Override
    public Genre createGenre(Genre genre) {
        return genreRepository.save(genre);
    }

    @Override
    public Genre updateGenre(Long genreId, Genre genre) {
        Optional<Genre> existingGenre = genreRepository.findById(genreId);
        if (existingGenre.isPresent()) {
            Genre genreUpdate = existingGenre.get();
            genreUpdate.setName(genre.getName());

            return genreRepository.save(genreUpdate);
        }

        return null;
    }

    @Override
    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    @Override
    public void deleteGenre(Long genreId) {
        genreRepository.deleteById(genreId);
    }
}
