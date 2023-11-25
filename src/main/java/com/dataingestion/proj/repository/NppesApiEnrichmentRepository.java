package com.dataingestion.proj.repository;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.dataingestion.proj.model.IngestedData;
import com.dataingestion.proj.model.NPPESData;
import com.dataingestion.proj.model.NPPESResponse;
import com.dataingestion.proj.model.NPPESResult;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class NppesApiEnrichmentRepository {
	private final JdbcTemplate jdbcTemplate;
	private static final String NPPES_API_BASE_URL = "https://npiregistry.cms.hhs.gov/api";
    private static final String API_VERSION = "2.1";


    @Autowired
    public NppesApiEnrichmentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    public void alterTable(int fileId) {
        StringBuilder alterTableQuery = new StringBuilder("ALTER TABLE file_data_" + fileId + " ");

        // Add columns 'taxonomy' and 'address'
        alterTableQuery.append("ADD COLUMN taxonomy VARCHAR(255), ADD COLUMN address VARCHAR(255);");

        // Execute the SQL query to alter the table using JdbcTemplate
        jdbcTemplate.execute(alterTableQuery.toString());
    }
    
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
    
    public static String getKeyByValue(Map<String, Object> map, Object value) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (value.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return null; // If the value is not found
    }

    
    public void updateTaxonomyAndAddress(int fileId) throws Exception{
        
        // SQL query to select rows with necessary columns
        String selectQuery = "SELECT * FROM file_data_" + fileId+ " " ;

        // Retrieve the rows using JdbcTemplate
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(selectQuery);

        // Update 'taxonomy' and 'address' columns based on the values of the first two columns
        for (Map<String, Object> row : rows) {
        	
        	for (Map.Entry<String, Object> entry : row.entrySet()) {
        	    String key = entry.getKey();
        	    Object value = entry.getValue();
        	    System.out.println(key + ": " + value);
        	}

        	String firstName = row.values().toArray()[0].toString();
           // String firstName = (String) row.get("providerFirstName");
            String lastName = (String) row.get("providerLastName");
            
            System.out.println(firstName);
            System.out.println(lastName);
            
            String columnName = getKeyByValue(row,firstName);
            
            
            // Fetch NPPES data based on provider information
            NPPESResponse nppesResponse = retrieveTaxonomyAndAddress(firstName, lastName);

            // SQL query to update 'taxonomy' and 'address' columns
            String updateQuery = "UPDATE file_data_" + fileId +
                    " SET taxonomy = ?, address = ? " +
                    " WHERE "+columnName+" = ? AND providerLastName = ?";
                            
            // Execute the SQL query to update the table using JdbcTemplate
            jdbcTemplate.update(updateQuery, nppesResponse.getTaxonomy(), nppesResponse.getAddress(), firstName, lastName);
        }
    }
}
