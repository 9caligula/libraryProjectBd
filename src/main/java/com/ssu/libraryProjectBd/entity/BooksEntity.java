package com.ssu.libraryProjectBd.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Books")
public class BooksEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", length = 6, precision = 10, nullable = false)
    private BigInteger id;

    @ManyToOne()
    @JoinColumn(name = "ID_Publishing_House", referencedColumnName = "id", nullable = false)
    private PublishingHouseEntity publishingHouse;

    @ManyToOne()
    @JoinColumn(name = "ID_Genre", referencedColumnName = "id", nullable = false)
    private GenreEntity genre;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "language", nullable = false)
    private String language;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    public static BooksEntity makeDefault(PublishingHouseEntity publishingHouse, GenreEntity genre, String name) {
        return builder()
                .genre(genre)
                .publishingHouse(publishingHouse)
                .name(name)
                .language("Русский")
                .quantity(1)
                .build();
    }
}
