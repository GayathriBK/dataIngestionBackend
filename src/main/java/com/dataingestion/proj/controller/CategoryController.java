package com.dataingestion.proj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.dataingestion.proj.model.Filecategories;
import com.dataingestion.proj.repository.CategoryRepository;
import com.dataingestion.proj.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/category")
@CrossOrigin(origins = "http://localhost:4200")
public class CategoryController {

	@Autowired
    private CategoryService categoryService;

	//This post request is used to add a category
    @PostMapping("/add")
    public String createStudent(@RequestBody Filecategories fileCategory) {
    	categoryService.addCategory(fileCategory);
        return "Saved successfully";
    }

    //This get request is used to return all categories present
    @GetMapping("/getall")
    public List<Filecategories> getAllFileCategories() {
        return (List<Filecategories>) categoryService.getAllCategories();
    }

}
