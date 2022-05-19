package com.ssu.libraryProjectBd.repository;

import com.ssu.libraryProjectBd.entity.BooksEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigInteger;

public interface BooksRepository extends JpaRepository<BooksEntity, BigInteger> {

    @Query(value = "select * from Books a where a.name = :name", nativeQuery = true)
    BooksEntity getBookByName(@Param("name") String name);

    @Modifying
    @Query(value = "delete from author_books_entities where books_entities_id = :id", nativeQuery = true)
    void deleteById(@Param("id") BigInteger id);
}