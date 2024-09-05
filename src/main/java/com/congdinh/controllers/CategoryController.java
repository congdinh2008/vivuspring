package com.congdinh.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.congdinh.services.CategoryService;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("")
    public String index(@RequestParam String keyword, @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size, Model model) {
        var categories = categoryService.search(keyword, page, size);
        model.addAttribute("categories", categories);
        return "categories/index";
    }
}
