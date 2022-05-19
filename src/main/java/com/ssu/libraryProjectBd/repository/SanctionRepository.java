package com.ssu.libraryProjectBd.repository;

import com.ssu.libraryProjectBd.entity.SanctionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigInteger;

public interface SanctionRepository extends JpaRepository<SanctionEntity, BigInteger> {

    @Query(value = "execute debtUser :idUser", nativeQuery = true)
    Float getDebtUser(@Param("idUser") BigInteger idUser);
}
