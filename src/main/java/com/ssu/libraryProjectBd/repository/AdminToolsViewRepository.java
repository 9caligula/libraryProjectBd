package com.ssu.libraryProjectBd.repository;

import com.ssu.libraryProjectBd.entity.view.AdminToolsView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.List;

@Repository
@Transactional
public interface AdminToolsViewRepository extends JpaRepository<AdminToolsView, BigInteger> {

    List<AdminToolsView> findAll();
}
