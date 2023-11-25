package com.dataingestion.proj.controller;

import com.dataingestion.proj.model.IngestedData;
import com.dataingestion.proj.model.IngestedFiles;
import com.dataingestion.proj.service.IngestedDataService;
import com.dataingestion.proj.service.NPPESApiService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/filedata")
@CrossOrigin(origins = "http://localhost:4200")
public class IngestedDataController {

    private final IngestedDataService ingestedDataService;
    private final NPPESApiService nppesApiService;

    @Autowired
    public IngestedDataController(NPPESApiService nppesApiService,IngestedDataService ingestedDataService) {
    	this.nppesApiService = nppesApiService;
        this.ingestedDataService = ingestedDataService;
    }

    //This post request is used to add the file contents to database
    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file,@RequestParam int categoryId) throws Exception {
    	if(categoryId == 1) {
    		int fileId = ingestedDataService.processFile(file,categoryId);
    		//enrichment
    		nppesApiService.alterTable(fileId);
    		nppesApiService.updateTaxonomyAndAddress(fileId);
            return "File uploaded and data processed successfully!";
    	}
    	return "Category ID invalid";
    }

    
    //This get request is used to retrieve the file status of all files
    @GetMapping("/files")
    public List<IngestedFiles> findAllFiles() {
        return ingestedDataService.findAllFiles();
    }
}

