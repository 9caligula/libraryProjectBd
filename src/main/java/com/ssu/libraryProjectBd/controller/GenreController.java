package com.ssu.libraryProjectBd.controller;

import com.ssu.libraryProjectBd.entity.GenreEntity;
import com.ssu.libraryProjectBd.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class GenreController {

    @Autowired
    private GenreService service;

    private static final String GET_ALL_GENRES = "/genres";

    @GetMapping(GET_ALL_GENRES)
    public String authors(Model model) {
        List<GenreEntity> list = service.getAllGenres();
        model.addAttribute("genres", list);
        return GET_ALL_GENRES;
    }


}
