package com.ssu.libraryProjectBd.repository;

import com.ssu.libraryProjectBd.entity.SupplierEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigInteger;

public interface SupplierRepository extends JpaRepository<SupplierEntity, BigInteger> {

    @Query(value = "select case when count(name) > 0 " +
            "then 1 else 0 end from supplier where name = :name", nativeQuery = true)
    Integer existByName(@Param("name") String name);

    @Query(value = "select * from supplier where name = :name", nativeQuery = true)
    SupplierEntity findByName(@Param("name") String name);
}
