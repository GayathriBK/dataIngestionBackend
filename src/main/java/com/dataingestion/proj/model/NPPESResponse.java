package com.dataingestion.proj.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NPPESResponse {
    private String taxonomy;
    private String address;
	public String getTaxonomy() {
		return taxonomy;
	}
	public void setTaxonomy(String taxonomy) {
		this.taxonomy = taxonomy;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

    // Getters and setters
    
}
