package io.github.tiagoadmstz.algamoney.api.controllers;

import io.github.tiagoadmstz.algamoney.api.models.Category;
import io.github.tiagoadmstz.algamoney.api.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping(produces = "application/json")
    public List<Category> list() {
        return categoryRepository.findAll();
    }

}
