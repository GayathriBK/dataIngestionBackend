package com.dataingestion.proj.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NPPESResult {
    private NPPESBasic basic;
    private NPPESAddress[] addresses;
    private NPPESTaxonomy[] taxonomies;
	public NPPESBasic getBasic() {
		return basic;
	}
	public void setBasic(NPPESBasic basic) {
		this.basic = basic;
	}
	public NPPESAddress[] getAddresses() {
		return addresses;
	}
	public void setAddresses(NPPESAddress[] addresses) {
		this.addresses = addresses;
	}
	public NPPESTaxonomy[] getTaxonomies() {
		return taxonomies;
	}
	public void setTaxonomies(NPPESTaxonomy[] taxonomies) {
		this.taxonomies = taxonomies;
	}

    // Getters and setters
    
}

