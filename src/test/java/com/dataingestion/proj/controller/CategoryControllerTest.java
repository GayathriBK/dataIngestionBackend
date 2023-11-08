package com.dataingestion.proj.controller;


import com.dataingestion.proj.model.Filecategories;
import com.dataingestion.proj.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitWebConfig
@SpringBootTest
public class CategoryControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private CategoryController categoryController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
    }

    @Test
    public void testAddCategory() throws Exception {
        Filecategories fileCategory = new Filecategories();
        fileCategory.setId(1); // Set appropriate values for your test
        fileCategory.setTransformation("toCsv");

        Mockito.doNothing().when(categoryService).addCategory(fileCategory);

        mockMvc.perform(MockMvcRequestBuilders.post("/category/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\": 1, \"name\": \"Test Category\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Saved successfully"));
    }


    @Test
    public void testGetAllFileCategories() throws Exception {
        List<Filecategories> fileCategories = new ArrayList<>();
        // Add some test data to the list

        Mockito.when(categoryService.getAllCategories()).thenReturn(fileCategories);

        mockMvc.perform(MockMvcRequestBuilders.get("/category/getall"))
                .andExpect(status().isOk());
                // You can also check the response content if needed.
    }
}
