package com.ssu.libraryProjectBd.controller;

import com.ssu.libraryProjectBd.entity.UserEntity;
import com.ssu.libraryProjectBd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new UserEntity());
        return "registration";
    }

    @PostMapping("/registration")
    public String createUser(@ModelAttribute("userForm") UserEntity user, Model model) {

        if (!user.getPassword().equals(user.getPasswordConfirm())){
            model.addAttribute("passwordError", "Пароли не совпадают");
            return "registration";
        }
        if (!userService.saveUser(user)){
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "registration";
        }
        String name = user.getUsername();
        String password = user.getPassword();
        String passwordConf = user.getPasswordConfirm();
        UserEntity entity = UserEntity.makeDefault(name, password, passwordConf);
        userService.saveUser(entity);

        return "redirect:/";
    }
}