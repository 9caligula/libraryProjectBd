package com.ssu.libraryProjectBd.controller;

import com.ssu.libraryProjectBd.entity.view.AuthorHasBooksView;
import com.ssu.libraryProjectBd.entity.IssuanceOfBookEntity;
import com.ssu.libraryProjectBd.entity.UserEntity;
import com.ssu.libraryProjectBd.repository.IssuanceOfBookRepository;
import com.ssu.libraryProjectBd.repository.SanctionRepository;
import com.ssu.libraryProjectBd.service.BooksService;
import com.ssu.libraryProjectBd.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AccountController {

    @Autowired
    UserService service;
    @Autowired
    BooksService booksService;

    private final IssuanceOfBookRepository repository;
    private final SanctionRepository sanctionRepository;

    @GetMapping("/account")
    public String findUser(Model model) {
        UserEntity currentUser = service.findCurrentUser();
        List<IssuanceOfBookEntity> list = repository.findByUserId(currentUser.getId());
        List<AuthorHasBooksView> hasBooksViewList = new ArrayList<>();
        for (IssuanceOfBookEntity entity: list) {
            hasBooksViewList.add(booksService.getBooksByName(entity.getBook().getBookName()).get(0));
        }
        model.addAttribute("debt", sanctionRepository.getDebtUser(currentUser.getId()));
        model.addAttribute("user", currentUser);
        model.addAttribute("books", hasBooksViewList);
        model.addAttribute("times", list);
        return "account";
    }

    // TODO update profile
}
