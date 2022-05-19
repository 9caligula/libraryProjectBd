package com.ssu.libraryProjectBd.repository;

import com.ssu.libraryProjectBd.entity.SupplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigInteger;
import java.util.Date;

public interface SupplyRepository extends JpaRepository<SupplyEntity, BigInteger> {

    @Query(value = "select * from supply where date_of_supply = :date and id_supplier = :id", nativeQuery = true)
    SupplyEntity findByParams(@Param("date")Date date, @Param("id") BigInteger id);
}
