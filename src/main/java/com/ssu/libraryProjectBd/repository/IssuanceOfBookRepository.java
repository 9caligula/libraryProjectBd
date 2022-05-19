package com.ssu.libraryProjectBd.repository;

import com.ssu.libraryProjectBd.entity.IssuanceOfBookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigInteger;
import java.util.List;

public interface IssuanceOfBookRepository extends JpaRepository<IssuanceOfBookEntity, BigInteger> {

    @Query(value = "select case when count(book_name) > 0 " +
            "then 1 else 0 end from issuance_of_book i where book_name = :name", nativeQuery = true)
    Integer existByName(@Param("name") String name);

    @Query(value = "select * from issuance_of_book where id_user = :user", nativeQuery = true)
    List<IssuanceOfBookEntity> findByUserId(@Param("user") BigInteger user);
}