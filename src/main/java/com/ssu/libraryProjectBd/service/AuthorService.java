package com.ssu.libraryProjectBd.service;

import com.ssu.libraryProjectBd.entity.AuthorEntity;
import com.ssu.libraryProjectBd.repository.AuthorRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class AuthorService {

    AuthorRepository repository;

    public List<AuthorEntity> getAllAuthors() {
        return repository.findAll();
    }

    public void createAuthor(String name, String date) {
        repository.saveAndFlush(AuthorEntity.makeDefault(name, date));
    }

    public AuthorEntity getAuthorByName(String name) {
        return repository.findAuthorByName(name);
    }
}
