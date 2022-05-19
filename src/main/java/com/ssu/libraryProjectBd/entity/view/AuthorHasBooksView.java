package com.ssu.libraryProjectBd.entity.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Immutable
@Table(name = "Author_Books_view")
@Subselect("select * from Author_Books_view")
public class AuthorHasBooksView implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", length = 6, precision = 10, nullable = false)
    private BigInteger id;

    @Column(name = "book_name", insertable = false, updatable = false)
    private String bookName;

    private String authorName;

    private String genreName;

    private String publishName;

    public String getReplaceName() {
        return Arrays.stream(authorName.split(" "))
                .map((i) -> i.substring(0, 1))
                .collect(Collectors.joining());
    }
}
