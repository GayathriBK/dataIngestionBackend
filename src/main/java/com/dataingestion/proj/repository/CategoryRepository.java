package com.dataingestion.proj.repository;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dataingestion.proj.model.Filecategories;

@Repository
public class CategoryRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CategoryRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Insert a new category into the database
    public void addCategory(Filecategories category) {
        String sql = "INSERT INTO filecategories (validation_, transformation, enrichment) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, category.getValidation(), category.getTransformation(), category.getEnrichment());
    }

    // Retrieve all categories from the database
    public List<Filecategories> getAllCategories() {
        String sql = "SELECT * FROM filecategories";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
        	Filecategories category = new Filecategories();
            category.setId(rs.getInt("id"));
            category.setValidation(rs.getString("validation_"));
            category.setTransformation(rs.getString("transformation"));
            category.setEnrichment(rs.getString("enrichment"));
            return category;
        });
    }
}
