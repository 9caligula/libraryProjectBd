package com.ssu.libraryProjectBd.repository;

import com.ssu.libraryProjectBd.entity.PublishingHouseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigInteger;

public interface PublishingHouseRepository extends JpaRepository<PublishingHouseEntity, BigInteger> {

    @Query(value = "select * from Publishing_House where name = :name", nativeQuery = true)
    PublishingHouseEntity findByName(@Param("name") String name);
}
