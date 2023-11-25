package com.dataingestion.proj.service.impl;

import com.dataingestion.proj.model.IngestedData;
import com.dataingestion.proj.model.IngestedFiles;
import com.dataingestion.proj.model.NPPESResponse;
import com.dataingestion.proj.repository.IngestedDataRepository;
import com.dataingestion.proj.repository.IngestedFilesRepository;
import com.dataingestion.proj.service.NPPESApiService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class IngestedDataCategory1ServiceImplTest {

    @InjectMocks
    private IngestedDataCategory1ServiceImpl ingestedDataService;

    @Mock
    private IngestedDataRepository ingestedDataRepository;

    @Mock
    private IngestedFilesRepository ingestedFilesRepository;

    @Mock
    private NPPESApiService nppesApiService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

//    @Test
//    public void testProcessFile() throws Exception {
//        MultipartFile file = createMockMultipartFile();
//        int categoryId = 1;
//        // Mock responses from your repositories and NPPESApiService
//        when(nppesApiService.retrieveTaxonomyAndAddress(Mockito.anyString(), Mockito.anyString())).thenReturn(new NPPESResponse());
//        
//        // Mock the IngestedData instance you expect to save
//        IngestedData expectedIngestedData = new IngestedData(); // Set your expected data here
//
//        // When addIngestedFiles is called, return the expected fileId
//        when(ingestedFilesRepository.addIngestedFiles(Mockito.any())).thenReturn(1);
//
//        // Call the method under test
//        ingestedDataService.processFile(file, categoryId);
//
//        // Verify that the expected interactions occurred
//        verify(ingestedFilesRepository, times(1)).addIngestedFiles(Mockito.any());
//    }

    
    @Test
    public void testFindAllFiles() {
        // Mock the list of IngestedFiles you expect to return from the repository
        List<IngestedFiles> expectedFilesList = new ArrayList<>(); // Add your expected data here
        when(ingestedFilesRepository.findAllFiles()).thenReturn(expectedFilesList);

        List<IngestedFiles> result = ingestedDataService.findAllFiles();

        // Assert that the result matches your expectations
        assertEquals(expectedFilesList, result);
    }

    private MultipartFile createMockMultipartFile() {
        // Create a mock MultipartFile instance using your preferred method
        // Example:
        return new MockMultipartFile("test.csv", "test.csv", "text/plain", new byte[0]);
    }
}

