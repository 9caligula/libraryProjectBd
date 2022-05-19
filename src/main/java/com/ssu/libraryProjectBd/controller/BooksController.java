package com.ssu.libraryProjectBd.controller;

import com.ssu.libraryProjectBd.entity.IssuanceOfBookEntity;
import com.ssu.libraryProjectBd.entity.UserEntity;
import com.ssu.libraryProjectBd.entity.view.AuthorHasBooksView;
import com.ssu.libraryProjectBd.repository.IssuanceOfBookRepository;
import com.ssu.libraryProjectBd.service.BooksService;
import com.ssu.libraryProjectBd.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class BooksController {

    @Autowired
    private final BooksService service;
    @Autowired
    private final UserService userService;

    private final IssuanceOfBookRepository repository;

    @RequestMapping(path = {"/books","/books/search"})
    public String getAllBooks(Model model, String keyword) {
        List<AuthorHasBooksView> list;
        if (keyword != null) {
            list = service.getBooksByName(keyword);
        } else {
            list = service.getAllView();
        }
        model.addAttribute("books", list);
        return "books";
    }

    @PostMapping("/books")
    public String createIssue(@RequestParam(value="action", required=true) String action, Model model) {
        List<AuthorHasBooksView> books = service.getBooksByName(action);
        UserEntity currentUser = userService.findCurrentUser();

        if (!repository.existByName(books.get(0).getBookName()).equals(1)) {
            repository.saveAndFlush(IssuanceOfBookEntity.makeDefault(currentUser, books.get(0)));
        }
        model.addAttribute("books", service.getAllView());
        return "books";
    }

    @GetMapping("/deleteAuthor")
    public String delete(@RequestParam String bookName) {
        service.deleteByName(bookName);
        return "redirect:/books";
    }

    @PostMapping("/saveBook")
    public String saveEmployee(@ModelAttribute("updatedBook") AuthorHasBooksView authorHasBooksView) {
        service.save(authorHasBooksView);
        return "redirect:/books";
    }

    @GetMapping("/showUpdateForBook/{id}")
    public String showUpdateForm(@PathVariable(value = "id") BigInteger id, Model model) {
        AuthorHasBooksView adminToolsView = service.findByID(id);
        model.addAttribute("updatedBook", adminToolsView);
        return "updateViewForBook";
    }

    @PostMapping("/bookss")
    public String create(String bookName,
                         String authorName,
                         String genreName,
                         String publishName) {

        service.createView(bookName, authorName, genreName, publishName);
        return "redirect:/books";
    }
}
