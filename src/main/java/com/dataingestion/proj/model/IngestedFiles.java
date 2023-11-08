package com.dataingestion.proj.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "ingested_files")
public class IngestedFiles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private int fileId;
    private String file_name;
    private LocalDateTime uploaded_date_time;
    private String status;
    private LocalDateTime ingested_date_time;
    private String error_message;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getFileId() {
		return fileId;
	}
	public void setFileId(int fileId) {
		this.fileId = fileId;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public LocalDateTime getUploaded_date_time() {
		return uploaded_date_time;
	}
	public void setUploaded_date_time(LocalDateTime uploaded_date_time) {
		this.uploaded_date_time = uploaded_date_time;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public LocalDateTime getIngested_date_time() {
		return ingested_date_time;
	}
	public void setIngested_date_time(LocalDateTime ingested_date_time) {
		this.ingested_date_time = ingested_date_time;
	}
	public String getError_message() {
		return error_message;
	}
	public void setError_message(String error_message) {
		this.error_message = error_message;
	}
    
    
}
