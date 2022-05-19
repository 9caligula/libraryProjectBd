package com.ssu.libraryProjectBd.controller;

import com.ssu.libraryProjectBd.entity.view.AdminToolsView;
import com.ssu.libraryProjectBd.service.AdminToolsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.Date;

@Controller
@RequiredArgsConstructor
public class AdminToolsController {

    @Autowired
    AdminToolsService service;

    @GetMapping("/admin")
    public String findAll(Model model) {
        model.addAttribute("views", service.findAll());
        return "adminTools";
    }

    @PostMapping("/admin")
    public String create(String bookName,
                         @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateOfSupply,
                         String nameSupplier,
                         Integer quantity) {

        service.createView(bookName, dateOfSupply, nameSupplier, quantity);
        return "redirect:/admin";
    }

    @GetMapping("/deleteView")
    public String delete(@RequestParam String bookName,
                         @RequestParam
                         @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateOfSupply,
                         @RequestParam String nameSupplier) {

        service.deleteById(nameSupplier, bookName, dateOfSupply);
        return "redirect:/admin";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("updatedView") AdminToolsView adminToolsView) {
        service.save(adminToolsView);
        return "redirect:/admin";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/showUpdateForm/{id}")
    public String showUpdateForm(@PathVariable(value = "id") BigInteger id, Model model) {
        AdminToolsView adminToolsView = service.findByID(id);
        model.addAttribute("updatedView", adminToolsView);
        return "updateViewForAdmin";
    }
}
