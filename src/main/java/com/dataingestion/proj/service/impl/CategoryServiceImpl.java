package com.dataingestion.proj.service.impl;

import com.dataingestion.proj.model.Filecategories;
import com.dataingestion.proj.repository.CategoryRepository;
import com.dataingestion.proj.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void addCategory(Filecategories category) {
        categoryRepository.addCategory(category);
    }

    @Override
    public List<Filecategories> getAllCategories() {
        return categoryRepository.getAllCategories();
    }
}