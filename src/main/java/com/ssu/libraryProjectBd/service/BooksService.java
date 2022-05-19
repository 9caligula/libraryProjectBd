package com.ssu.libraryProjectBd.service;

import com.ssu.libraryProjectBd.entity.*;
import com.ssu.libraryProjectBd.entity.view.AuthorHasBooksView;
import com.ssu.libraryProjectBd.repository.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.List;

@Service
@Transactional
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class BooksService {

    AuthorHasBooksViewRepository authorHasBooksViewRepository;
    BooksRepository booksRepository;
    BooksInDeliveryRepository booksInDeliveryRepository;
    GenreRepository genreRepository;
    PublishingHouseRepository publishingHouseRepository;
    AuthorRepository authorRepository;

    public List<AuthorHasBooksView> getAllView() {
        return authorHasBooksViewRepository.findAll();
    }

    public List<AuthorHasBooksView> getBooksByName(String keyword) {
        return authorHasBooksViewRepository.getBooksByName(keyword);
    }

    public List<AuthorHasBooksView> getBooksByAuthor(String authorName) {
        return authorHasBooksViewRepository.findBooksByAuthor(authorName);
    }

    public void deleteByName(String name) {
        BigInteger id = authorHasBooksViewRepository.getBooksByName(name).get(0).getId();
        booksInDeliveryRepository.deleteById(id);
        booksRepository.deleteById(id);
    }

    public void save(AuthorHasBooksView authorHasBooksView) {
        BooksEntity entity = booksRepository.getById(authorHasBooksView.getId());
        entity.setName(authorHasBooksView.getBookName());
        entity.setGenre(genreRepository.getById(genreRepository.findByName(authorHasBooksView.getGenreName()).getId()));
        entity.setPublishingHouse(publishingHouseRepository.getById(publishingHouseRepository.findByName(authorHasBooksView.getPublishName()).getId()));
        booksRepository.saveAndFlush(entity);
    }

    public AuthorHasBooksView findByID(BigInteger id) {
        return authorHasBooksViewRepository.getById(id);
    }

    public void createView(String bookName, String authorName, String genreName, String publishName) {
        if (bookName != null && authorName != null && genreName != null && publishName != null) {

            booksRepository.saveAndFlush(BooksEntity.makeDefault(publishingHouseRepository.findByName(publishName),
                    genreRepository.findByName(genreName), bookName));
        }
    }
}
