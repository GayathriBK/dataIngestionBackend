package com.dataingestion.proj.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "file_categories")
public class Filecategories {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String validation;
    private String transformation;
    private String enrichment;

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getValidation() {
		return validation;
	}

	public void setValidation(String validation) {
		this.validation = validation;
	}

	public String getTransformation() {
		return transformation;
	}

	public void setTransformation(String transformation) {
		this.transformation = transformation;
	}

	public String getEnrichment() {
		return enrichment;
	}

	public void setEnrichment(String enrichment) {
		this.enrichment = enrichment;
	}

	public Filecategories() {
        // For persistence
    }

}
