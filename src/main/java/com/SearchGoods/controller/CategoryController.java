package com.SearchGoods.controller;

import com.SearchGoods.exception.ResourceNotFoundException;
import com.SearchGoods.model.Categories;
import com.SearchGoods.repository.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    CategoriesRepository categoriesRepository;

    @GetMapping("/cats")
    public List<Categories> getAllCats() {
        return categoriesRepository.findAll();
    }

    @PostMapping("/cats")
    public Categories createCat(@Valid @RequestBody Categories categories) {
        return categoriesRepository.save(categories);
    }

    @GetMapping("/cats/{cat_id}")
    public Categories getCatsById(@PathVariable(value = "cat_id") Long catId) {
        return categoriesRepository.findById(catId)
                .orElseThrow(() -> new ResourceNotFoundException("Categories", "cat_id", catId));
    }

    @PutMapping("/cats/{cat_id}")
    public Categories updateCat(@PathVariable(value = "cat_id") Long catId,
                            @Valid @RequestBody Categories catDetails) {

        Categories categories = categoriesRepository.findById(catId)
                .orElseThrow(() -> new ResourceNotFoundException("Categories", "cat_id", catId));

        categories.setName(catDetails.getName());

        Categories updatedCat = categoriesRepository.save(categories);
        return updatedCat;
    }

    @DeleteMapping("/cats/{cat_id}")
    public ResponseEntity<?> deleteGood(@PathVariable(value = "cat_id") Long catId) {
        Categories categories = categoriesRepository.findById(catId)
                .orElseThrow(() -> new ResourceNotFoundException("Categories", "cat_id", catId));

        categoriesRepository.delete(categories);

        return ResponseEntity.ok().build();
    }
}
