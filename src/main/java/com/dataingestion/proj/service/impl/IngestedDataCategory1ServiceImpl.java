package com.dataingestion.proj.service.impl;
import com.dataingestion.proj.model.FileCategoryMapping;
import com.dataingestion.proj.model.IngestedData;
import com.dataingestion.proj.model.IngestedFiles;
import com.dataingestion.proj.model.NPPESData;
import com.dataingestion.proj.model.NPPESResponse;
import com.dataingestion.proj.repository.IngestedDataRepository;
import com.dataingestion.proj.repository.IngestedFilesRepository;
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
public class IngestedDataCategory1ServiceImpl implements IngestedDataService {

    private final IngestedDataRepository ingestedDataRepository;
    private final IngestedFilesRepository ingestedFilesRepository;
    private final RestTemplate restTemplate;

    @Autowired
    private NPPESApiService nppesApiService;

    @Autowired
    public IngestedDataCategory1ServiceImpl(IngestedDataRepository ingestedDataRepository, IngestedFilesRepository ingestedFilesRepository,RestTemplate restTemplate) {
        this.ingestedDataRepository = ingestedDataRepository;
        this.ingestedFilesRepository = ingestedFilesRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public void processFile(MultipartFile file, int categoryID) {
    	IngestedFiles ingestedFile = new IngestedFiles();
    	ingestedFile.setFile_name(file.getOriginalFilename());
    	//Adding a new file to database and getting the file ID of it 
    	int fileId = ingestedFilesRepository.addIngestedFiles(ingestedFile);
    	ingestedFile.setFileId(fileId);
    	
    	//Add Category File Mapping
    	FileCategoryMapping fileCategoryMapping = new FileCategoryMapping();
    	fileCategoryMapping.setCategoryId(categoryID);
    	fileCategoryMapping.setFileId(fileId);
    	ingestedFilesRepository.addFileCategoryMapping(fileCategoryMapping);
    	
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build(); // Skip the header line

            String[] line;
            while ((line = csvReader.readNext()) != null) {
                // Extract CSV data and convert it to JSON format
                String providerFirstName = line[0];
                String providerLastName = line[1];
                String ssn = line[2];
                String providerStreet = line[3];
                String providerCity = line[4];
                String providerState = line[5];
                String providerZip = line[6];

                // Fetch NPPES data based on provider information
                NPPESResponse nppesResponse = nppesApiService.retrieveTaxonomyAndAddress(providerFirstName, providerLastName);
                
                // Save data in the database after enrichment
                IngestedData ingestedData = new IngestedData();
                ingestedData.setFileId(fileId);
                ingestedData.setProviderFirstName(providerFirstName);
                ingestedData.setProviderLastName(providerLastName);
                ingestedData.setSsn(ssn);
            	ingestedData.setProviderServiceLocationStreet(providerStreet);
            	ingestedData.setProviderServiceLocationCity(providerCity);
            	ingestedData.setProviderServiceLocationState(providerState);
            	ingestedData.setProviderServiceLocationZip(providerZip);
            	ingestedData.setNppesTaxonomy(nppesResponse.getTaxonomy());
            	ingestedData.setNppesAddress(nppesResponse.getAddress());
                ingestedDataRepository.addData(ingestedData);
                
                ingestedFilesRepository.updateIngestedFiles(ingestedFile);
            }
        } catch (CsvValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ingestedFile.setError_message("Csv Validation Exception");
			ingestedFilesRepository.updateIngestedFileError(ingestedFile);
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ingestedFile.setError_message("Rest Client Exception");
			ingestedFilesRepository.updateIngestedFileError(ingestedFile);
		} catch(Exception e) {
			e.printStackTrace();
			ingestedFile.setError_message("Exception in NPPES API detch");
			ingestedFilesRepository.updateIngestedFileError(ingestedFile);
		}
    }

    @Override
    public List<IngestedData> getAllFileData() {
        return ingestedDataRepository.findAll();
    }
    
    @Override
    public List<IngestedFiles> findAllFiles(){
    	return ingestedFilesRepository.findAllFiles();
    }
}
