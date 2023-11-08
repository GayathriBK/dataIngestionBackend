package com.dataingestion.proj.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NPPESData {
    private NPPESResult[] results;

	public NPPESResult[] getResults() {
		return results;
	}

	public void setResults(NPPESResult[] results) {
		this.results = results;
	}

    // Getters and setters
    
}


