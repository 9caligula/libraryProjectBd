package com.ssu.libraryProjectBd.service;

import com.ssu.libraryProjectBd.entity.GenreEntity;
import com.ssu.libraryProjectBd.repository.GenreRepository;
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
public class GenreService {

    GenreRepository repository;

    public List<GenreEntity> getAllGenres() {
        return repository.findAll();
    }

}
