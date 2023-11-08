package com.dataingestion.proj.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ingested_data")
public class IngestedData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private int fileId;
    private String providerFirstName;
    private String providerLastName;
    private String ssn;
    private String providerServiceLocationStreet;
    private String providerServiceLocationCity;
    private String providerServiceLocationState;
    private String providerServiceLocationZip;
    private String nppesTaxonomy;
    private String nppesAddress;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProviderFirstName() {
		return providerFirstName;
	}
	public void setProviderFirstName(String providerFirstName) {
		this.providerFirstName = providerFirstName;
	}
	public String getProviderLastName() {
		return providerLastName;
	}
	public void setProviderLastName(String providerLastName) {
		this.providerLastName = providerLastName;
	}
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public String getProviderServiceLocationStreet() {
		return providerServiceLocationStreet;
	}
	public void setProviderServiceLocationStreet(String providerServiceLocationStreet) {
		this.providerServiceLocationStreet = providerServiceLocationStreet;
	}
	public String getProviderServiceLocationCity() {
		return providerServiceLocationCity;
	}
	public void setProviderServiceLocationCity(String providerServiceLocationCity) {
		this.providerServiceLocationCity = providerServiceLocationCity;
	}
	public String getProviderServiceLocationState() {
		return providerServiceLocationState;
	}
	public void setProviderServiceLocationState(String providerServiceLocationState) {
		this.providerServiceLocationState = providerServiceLocationState;
	}
	public String getProviderServiceLocationZip() {
		return providerServiceLocationZip;
	}
	public void setProviderServiceLocationZip(String providerServiceLocationZip) {
		this.providerServiceLocationZip = providerServiceLocationZip;
	}
	public String getNppesTaxonomy() {
		return nppesTaxonomy;
	}
	public void setNppesTaxonomy(String nppesTaxonomy) {
		this.nppesTaxonomy = nppesTaxonomy;
	}
	public String getNppesAddress() {
		return nppesAddress;
	}
	public void setNppesAddress(String nppesAddress) {
		this.nppesAddress = nppesAddress;
	}
	public int getFileId() {
		return fileId;
	}
	public void setFileId(int fileId) {
		this.fileId = fileId;
	}
    
    
}

