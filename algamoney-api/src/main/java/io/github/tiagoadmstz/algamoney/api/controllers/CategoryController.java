package io.github.tiagoadmstz.algamoney.api.controllers;

import io.github.tiagoadmstz.algamoney.api.models.Category;
import io.github.tiagoadmstz.algamoney.api.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public List<Category> list() {
        return categoryRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id) {
        return categoryRepository
                .findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Category> create(@RequestBody Category category, HttpServletResponse response) {
        Category categorySaved = categoryRepository.save(category);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(categorySaved.getId())
                .toUri();
        response.setHeader("Location", uri.toASCIIString());

        return ResponseEntity.created(uri).body(category);
    }

}
