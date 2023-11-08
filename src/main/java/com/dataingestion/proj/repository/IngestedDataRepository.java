package com.dataingestion.proj.repository;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dataingestion.proj.model.IngestedData;

@Repository
public class IngestedDataRepository {
	private final JdbcTemplate jdbcTemplate;

    @Autowired
    public IngestedDataRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    //Add file data after enrichment
    public void addData(IngestedData ingestedData) {
        String sql = "INSERT INTO ingested_data (file_id,provider_first_name, provider_last_name, ssn ,provider_service_location_street, provider_service_location_city,provider_service_location_state,provider_service_location_zip,nppes_taxonomy,nppes_address) VALUES (?, ?, ?,?, ?, ?,?, ?, ?,?)";
        jdbcTemplate.update(sql, ingestedData.getFileId(),ingestedData.getProviderFirstName(),ingestedData.getProviderLastName(), ingestedData.getSsn(), ingestedData.getProviderServiceLocationStreet(),ingestedData.getProviderServiceLocationCity(),ingestedData.getProviderServiceLocationState(),ingestedData.getProviderServiceLocationZip(),ingestedData.getNppesTaxonomy(),ingestedData.getNppesAddress());
    }
    
    //Retriev all file data
    public List<IngestedData> findAll() {
        String sql = "SELECT * FROM ingested_data";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
        	IngestedData ingestedData = new IngestedData();
        	ingestedData.setFileId(rs.getInt("file_id"));
        	ingestedData.setProviderFirstName(rs.getString("provider_first_name"));
        	ingestedData.setProviderLastName(rs.getString("provider_last_name"));
        	ingestedData.setSsn(rs.getString("ssn"));
        	ingestedData.setProviderServiceLocationStreet(rs.getString("provider_service_location_street"));
        	ingestedData.setProviderServiceLocationCity(rs.getString("provider_service_location_city"));
        	ingestedData.setProviderServiceLocationState(rs.getString("provider_service_location_state"));
        	ingestedData.setProviderServiceLocationZip(rs.getString("provider_service_location_zip"));
        	ingestedData.setNppesTaxonomy(rs.getString("nppes_taxonomy"));
        	ingestedData.setNppesAddress(rs.getString("nppes_address"));
            return ingestedData;
        });
    }
    


}
