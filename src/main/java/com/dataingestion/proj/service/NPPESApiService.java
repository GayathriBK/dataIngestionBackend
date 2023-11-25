package com.dataingestion.proj.service;


public interface NPPESApiService {
	void alterTable(int fileId);
	void updateTaxonomyAndAddress(int fileId) throws Exception;
    
}

