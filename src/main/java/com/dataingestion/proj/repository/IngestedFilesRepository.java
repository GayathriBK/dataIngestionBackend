package com.dataingestion.proj.repository;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dataingestion.proj.model.FileCategoryMapping;
import com.dataingestion.proj.model.IngestedData;
import com.dataingestion.proj.model.IngestedFiles;

@Repository
public class IngestedFilesRepository {
	private final JdbcTemplate jdbcTemplate;

    @Autowired
    public IngestedFilesRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Insert a new file into the database
    public int addIngestedFiles(IngestedFiles ingestedFiles) {
        String sql = "INSERT INTO ingested_files (file_name, uploaded_date_time, status)\r\n"
        		+ "VALUES (?, NOW(), 'Ingesting');\r\n"
        		+ "";
        jdbcTemplate.update(sql, ingestedFiles.getFile_name());
        int fileId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        return fileId;
    }
    
    //Update file status
    public void updateIngestedFiles(IngestedFiles ingestedFiles) {
        String sql = "UPDATE ingested_files\r\n"
        		+ "SET status = 'Ingested', ingested_date_time = NOW()\r\n"
        		+ "WHERE id = ?;\r\n"
        		+ "";
        jdbcTemplate.update(sql, ingestedFiles.getFileId());
    }
    
    //Update error status
    public void updateIngestedFileError(IngestedFiles ingestedFiles) {
        String sql = "INSERT INTO ingested_files (file_id, status,error_message)\r\n"
        		+ "VALUES (?, 'Failed',?);";
        jdbcTemplate.update(sql, ingestedFiles.getFileId(),ingestedFiles.getError_message());
    }
    
    //Retrieve all files and their status
    public List<IngestedFiles> findAllFiles() {
        String sql = "SELECT * FROM ingested_files";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
        	IngestedFiles ingestedFiles = new IngestedFiles();
        	ingestedFiles.setFileId(rs.getInt("id"));
        	ingestedFiles.setFile_name(rs.getString("file_name"));
        	ingestedFiles.setIngested_date_time(rs.getTimestamp("ingested_date_time").toLocalDateTime());
        	ingestedFiles.setUploaded_date_time(rs.getTimestamp("uploaded_date_time").toLocalDateTime());
        	ingestedFiles.setStatus(rs.getString("status"));
        	ingestedFiles.setError_message(rs.getString("error_message"));
            return ingestedFiles;
        });
    }
    
    public void addFileCategoryMapping(FileCategoryMapping fileCategoryMapping) {
        String sql = "INSERT INTO file_category_mapping (file_id, category_id)\r\n"
        		+ "VALUES (?, ?);\r\n"
        		+ "";
        jdbcTemplate.update(sql, fileCategoryMapping.getFileId(),fileCategoryMapping.getCategoryId());
    }
}
