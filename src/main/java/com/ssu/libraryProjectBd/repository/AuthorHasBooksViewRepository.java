package com.ssu.libraryProjectBd.repository;

import com.ssu.libraryProjectBd.entity.view.AuthorHasBooksView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.List;

@Repository
@Transactional
public interface AuthorHasBooksViewRepository extends JpaRepository<AuthorHasBooksView, BigInteger> {

    List<AuthorHasBooksView> findAll();

    @Query(value = "select * from Author_Books_view a where a.book_name like :keyword%", nativeQuery = true)
    List<AuthorHasBooksView> getBooksByName(@Param("keyword") String keyword);

    @Query(value = "select * from Author_Books_view a where a.author_name = :authorName", nativeQuery = true)
    List<AuthorHasBooksView> findBooksByAuthor(@Param("authorName") String authorName);
}
