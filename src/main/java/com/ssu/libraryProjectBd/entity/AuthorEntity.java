package com.ssu.libraryProjectBd.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Author")
public class AuthorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", length = 6, precision = 10, nullable = false)
    private BigInteger Id;

    @Column(name = "Full_name")
    private String fullName;

    @Column(name = "Date_of_birth")
    private String dateOfBirth;

    @Column(name = "description")
    private String description;

    @ManyToMany()
    Set<BooksEntity> booksEntities;

    public static AuthorEntity makeDefault(String name, String date) {
        return builder()
                .fullName(name)
                .dateOfBirth(date)
                .build();
    }

    public String getReplaceName() {
        return Arrays.stream(fullName.split(" "))
                .map((i) -> i.substring(0, 1))
                .collect(Collectors.joining());
    }
}
