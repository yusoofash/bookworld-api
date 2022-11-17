package com.bookworld.api.book;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.Set;

@Getter
@Setter
public class BookPostDto {
    @JsonProperty("title")
    private String title;

    @JsonProperty("publicationDate")
    private Date publicationDate;

    @JsonProperty("price")
    private Long price;

    @JsonProperty("quantity")
    private int quantity;

    @JsonProperty("authorId")
    private Long authorId;

    @JsonProperty("publisherId")
    private Long publisherId;

    @JsonProperty("genreIds")
    private Set<Long> genreIds;

}
