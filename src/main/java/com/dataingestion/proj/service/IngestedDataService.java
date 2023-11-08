package com.dataingestion.proj.service;

import org.springframework.web.multipart.MultipartFile;

import com.dataingestion.proj.model.IngestedData;
import com.dataingestion.proj.model.IngestedFiles;

import java.io.IOException;
import java.util.List;

public interface IngestedDataService {
	//Under this interface multiple impl files can be created for different categories
	//Since for now there is only 1 category (csv-to-json + nppess enrichment) only 1 impl file is there
    void processFile(MultipartFile file, int categoryID) throws IOException;
    List<IngestedData> getAllFileData();
    List<IngestedFiles> findAllFiles();
}

