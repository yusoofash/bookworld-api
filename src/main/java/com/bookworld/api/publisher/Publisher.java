package com.bookworld.api.publisher;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Publisher {
    @Id
    @SequenceGenerator(name = "publisher_seq", sequenceName = "publisher_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "publisher_seq")
    private Long id;

    private String name;
}
