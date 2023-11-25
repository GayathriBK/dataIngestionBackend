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
    
 
    public void createTable(String[] headers, int fileId) {
        StringBuilder createTableQuery = new StringBuilder("CREATE TABLE IF NOT EXISTS file_data_" + fileId + " (");

        for (String header : headers) {
            // Use a generic data type for all columns (e.g., VARCHAR) since the data types are unknown
        	header = header.trim();
            createTableQuery.append(header).append(" VARCHAR(255), ");
        }

        // Remove the trailing comma and add a closing parenthesis
        createTableQuery.setLength(createTableQuery.length() - 2);
        createTableQuery.append(");");
        System.out.println(createTableQuery.toString());
        // Execute the SQL query to create the table
        jdbcTemplate.execute(createTableQuery.toString());
    }
    
    public void insertRecordIntoTable(String[] record, int fileId) {
        StringBuilder insertQuery = new StringBuilder("INSERT INTO file_data_" + fileId );

        
        insertQuery.append(" VALUES (");

        for (String value : record) {
            insertQuery.append("'").append(value).append("', ");
        }

        // Remove the trailing comma and add a closing parenthesis
        insertQuery.setLength(insertQuery.length() - 2);
        insertQuery.append(");");

        // Execute the SQL query to insert the record using JdbcTemplate
        jdbcTemplate.execute(insertQuery.toString());
    }

}
