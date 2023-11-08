package com.dataingestion.proj.service;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.dataingestion.proj.model.NPPESData;
import com.dataingestion.proj.model.NPPESResponse;
import com.dataingestion.proj.model.NPPESResult;

@Service
public class NPPESApiService {
    private static final String NPPES_API_BASE_URL = "https://npiregistry.cms.hhs.gov/api";
    private static final String API_VERSION = "2.1";

    public NPPESResponse retrieveTaxonomyAndAddress(String firstName, String lastName) throws Exception {
        RestTemplate restTemplate = new RestTemplate();

        String searchCriteria = "first_name=" + firstName + "&last_name=" + lastName;
        String apiUrl = NPPES_API_BASE_URL + "?" + searchCriteria + "&version=" + API_VERSION;

        ResponseEntity<String> response = restTemplate.exchange(
            apiUrl,
            HttpMethod.GET,
            null,
            String.class
        );
        
        

        if (response.getStatusCode() == HttpStatus.OK) {
        	String jsonResponse = response.getBody();
            
            // Deserialize JSON response using Jackson
            ObjectMapper objectMapper = new ObjectMapper();
            NPPESData nppesData = objectMapper.readValue(jsonResponse, NPPESData.class);
            
            // Access the taxonomy and address information from the deserialized object
            NPPESResult result = nppesData.getResults()[0]; // Assuming there is only one result
            String taxonomyDescription = result.getTaxonomies()[0].getDesc();
            String addressPurpose = result.getAddresses()[0].getAddress_purpose();
            String address1 = result.getAddresses()[0].getAddress_1();
            String city = result.getAddresses()[0].getCity();
            String state = result.getAddresses()[0].getState();
            String postalCode = result.getAddresses()[0].getPostal_code();
            NPPESResponse nppesResponse = new NPPESResponse();
            nppesResponse.setAddress(address1+", "+city+", "+state+", "+postalCode);
            nppesResponse.setTaxonomy(taxonomyDescription);
            return nppesResponse;
        } else {
            // Handle API request errors
            throw new Exception("Error while accessing NPPES API");
        }
    }
}

