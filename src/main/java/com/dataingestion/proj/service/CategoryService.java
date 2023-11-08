package com.dataingestion.proj.service;

import java.util.List;

import com.dataingestion.proj.model.Filecategories;

public interface CategoryService {
	 void addCategory(Filecategories category);

	 List<Filecategories> getAllCategories();

}
