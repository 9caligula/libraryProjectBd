package com.ssu.libraryProjectBd.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigInteger;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Books_In_Delivery")
public class BooksInDelivery {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", length = 6, precision = 10, nullable = false)
    private BigInteger id;

    @ManyToOne()
    @JoinColumn(name = "ID_Supply", referencedColumnName = "id", nullable = false)
    private SupplyEntity supplyEntity;

    @ManyToOne()
    @JoinColumn(name = "ID_Book", referencedColumnName = "id", nullable = false)
    private BooksEntity booksEntity;

    private Integer quantity;

    public static BooksInDelivery makeDefault(SupplyEntity supply, BooksEntity books, Integer quantity) {
        return builder()
                .supplyEntity(supply)
                .booksEntity(books)
                .quantity(quantity)
                .build();
    }
}
