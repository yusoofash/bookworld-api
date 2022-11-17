package com.bookworld.api.genre;

import com.bookworld.api.book.Book;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Genre {

    @Id
    @SequenceGenerator(name = "genre_seq", sequenceName = "genre_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genre_seq")
    private Long id;

    private String name;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "genres")
    @JsonBackReference
    private List<Book> books;
}
