package com.dataingestion.proj.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.dataingestion.proj.model.Filecategories;
import com.dataingestion.proj.repository.CategoryRepository;

public class CategoryServiceImplTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddCategory() {
        Filecategories category = new Filecategories();
        category.setId(0);

        doNothing().when(categoryRepository).addCategory(category);

        categoryService.addCategory(category);

        verify(categoryRepository, times(1)).addCategory(category);
    }

    @Test
    public void testGetAllCategories() {
        List<Filecategories> categories = new ArrayList<>();
        Filecategories category1 = new Filecategories();
        category1.setId(0);
        category1.setTransformation("Category 1");
        
        Filecategories category2 = new Filecategories();
        category2.setId(1);
        category2.setTransformation("Category 2");
        
        categories.add(new Filecategories());
        categories.add(new Filecategories());

        when(categoryRepository.getAllCategories()).thenReturn(categories);

        List<Filecategories> result = categoryService.getAllCategories();

        verify(categoryRepository, times(1)).getAllCategories();

        assertEquals(2, result.size());
    }
}
