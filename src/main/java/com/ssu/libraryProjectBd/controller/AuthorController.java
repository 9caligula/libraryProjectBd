package com.ssu.libraryProjectBd.controller;

import com.ssu.libraryProjectBd.entity.AuthorEntity;
import com.ssu.libraryProjectBd.entity.view.AuthorHasBooksView;
import com.ssu.libraryProjectBd.service.AuthorService;
import com.ssu.libraryProjectBd.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AuthorController {

    @Autowired
    private AuthorService service;
    @Autowired
    private BooksService booksService;

    public static final String GET_ALL_AUTHORS = "/authors";
    public static final String CREATE_AUTHOR = "/createAuthor";
    public static final String GET_AUTHOR_BY_NAME = "/author/{name}";

    @GetMapping({"/", "/index"})
    public String index(Model model) {
        return "index";
    }

    @GetMapping(GET_ALL_AUTHORS)
    public String authors(Model model) {
        List<AuthorEntity> list = service.getAllAuthors();
        model.addAttribute("authors", list);
        return "authors";
    }

    @GetMapping(CREATE_AUTHOR)
    public String showCreateAuthorPage(Model model) {
        AuthorEntity authorEntity = new AuthorEntity();
        model.addAttribute("authorForm", authorEntity);
        return CREATE_AUTHOR;
    }

    @PostMapping(CREATE_AUTHOR)
    public String createAuthor(@ModelAttribute("authorForm") AuthorEntity entity) {
        String name = entity.getFullName();
        String date = entity.getDateOfBirth();

        if (name != null && name.length() > 0
                && date != null && date.length() > 0) {
            service.createAuthor(name, date);
            return "redirect:/authors";
        }

        // TODO: refactoring this code, need move to service
        return CREATE_AUTHOR;
    }

    @GetMapping(GET_AUTHOR_BY_NAME)
    public String getAuthorByName(@PathVariable String name, Model model) {
        AuthorEntity authorEntity = service.getAuthorByName(name);
        // TODO: if not found + NPE
        List<AuthorHasBooksView> listBooks = booksService.getBooksByAuthor(name);
        model.addAttribute("author", authorEntity);
        model.addAttribute("books", listBooks);
        return "authorName";
    }
}
