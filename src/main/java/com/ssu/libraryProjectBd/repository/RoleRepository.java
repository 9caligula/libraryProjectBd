package com.ssu.libraryProjectBd.repository;

import com.ssu.libraryProjectBd.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface RoleRepository extends JpaRepository<RoleEntity, BigInteger> {
}