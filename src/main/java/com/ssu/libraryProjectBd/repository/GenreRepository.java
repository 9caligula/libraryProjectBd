package com.ssu.libraryProjectBd.repository;

import com.ssu.libraryProjectBd.entity.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigInteger;

public interface GenreRepository extends JpaRepository<GenreEntity, BigInteger> {

    @Query(value = "select * from genre where name = :name", nativeQuery = true)
    GenreEntity findByName(@Param("name") String name);
}
