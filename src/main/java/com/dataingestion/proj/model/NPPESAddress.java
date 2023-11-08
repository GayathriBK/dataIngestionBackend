package com.dataingestion.proj.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NPPESAddress {
    private String address_purpose;
    private String address_1;
    private String city;
    private String state;
    private String postal_code;
    // Getters and setters
	public String getAddress_purpose() {
		return address_purpose;
	}
	public void setAddress_purpose(String address_purpose) {
		this.address_purpose = address_purpose;
	}
	public String getAddress_1() {
		return address_1;
	}
	public void setAddress_1(String address_1) {
		this.address_1 = address_1;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPostal_code() {
		return postal_code;
	}
	public void setPostal_code(String postal_code) {
		this.postal_code = postal_code;
	}
    
    
}
