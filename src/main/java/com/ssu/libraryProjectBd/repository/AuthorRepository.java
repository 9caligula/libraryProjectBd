package com.ssu.libraryProjectBd.repository;

import com.ssu.libraryProjectBd.entity.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigInteger;

public interface AuthorRepository extends JpaRepository<AuthorEntity, BigInteger> {
    @Query(value = "select * from author where full_name = :name", nativeQuery = true)
    AuthorEntity findAuthorByName(@Param("name") String name);
}
