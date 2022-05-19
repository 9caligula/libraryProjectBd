package com.ssu.libraryProjectBd.repository;

import com.ssu.libraryProjectBd.entity.BooksInDelivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigInteger;

public interface BooksInDeliveryRepository extends JpaRepository<BooksInDelivery, BigInteger> {

    @Modifying
    @Query(value = "delete from books_in_delivery where id_supply = :idS and id_book = :idB", nativeQuery = true)
    void deleteByIds(@Param("idS") BigInteger idS, @Param("idB") BigInteger idB);

    @Query(value = "select * from books_in_delivery where id_supply = :idS and id_book = :idB", nativeQuery = true)
    BooksInDelivery findByParams(@Param("idS") BigInteger idS, @Param("idB") BigInteger idB);

    @Modifying
    @Query(value = "delete from books_in_delivery where id_book = :id", nativeQuery = true)
    void deleteById(@Param("id") BigInteger id);
}
