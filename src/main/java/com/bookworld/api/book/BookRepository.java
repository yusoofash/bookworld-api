package com.bookworld.api.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query(value = "SELECT b FROM book b INNER JOIN book_genre_map bgm ON b.bookId = bgm.book_id " +
            "WHERE bgm.genre_id IN :genreIds", nativeQuery = true)
    List<Book> findByGenreIds(@Param("genreIds") Set<Long> genreIds);
}
