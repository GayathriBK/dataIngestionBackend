package com.dataingestion.proj.service.impl;
import com.dataingestion.proj.model.FileCategoryMapping;
import com.dataingestion.proj.model.IngestedData;
import com.dataingestion.proj.model.IngestedFiles;
import com.dataingestion.proj.model.NPPESData;
import com.dataingestion.proj.model.NPPESResponse;
import com.dataingestion.proj.repository.IngestedDataRepository;
import com.dataingestion.proj.repository.NppesApiEnrichmentRepository;
import com.dataingestion.proj.service.IngestedDataService;
import com.dataingestion.proj.service.NPPESApiService;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

@Service
public class NPPESApiServiceImpl implements NPPESApiService{
	private final NppesApiEnrichmentRepository nppesApiEnrichmentRepository;
    private final RestTemplate restTemplate;

    
    @Autowired
    public NPPESApiServiceImpl( NppesApiEnrichmentRepository nppesApiEnrichmentRepository,RestTemplate restTemplate) {
        this.nppesApiEnrichmentRepository = nppesApiEnrichmentRepository;
        this.restTemplate = restTemplate;
    }
    
    
    public void alterTable(int fileId) {
    	nppesApiEnrichmentRepository.alterTable(fileId);
    }
    
    public void updateTaxonomyAndAddress(int fileId) throws Exception {
    	nppesApiEnrichmentRepository.updateTaxonomyAndAddress(fileId);
    }
}
