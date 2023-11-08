package com.dataingestion.proj.controller;

import com.dataingestion.proj.model.IngestedData;
import com.dataingestion.proj.model.IngestedFiles;
import com.dataingestion.proj.service.IngestedDataService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@SpringBootTest
public class IngestedDataControllerTest {

    private MockMvc mockMvc;

    @Mock
    private IngestedDataService ingestedDataService;

    @InjectMocks
    private IngestedDataController ingestedDataController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(ingestedDataController).build();
    }

    @Test
    public void testUploadFileSuccess() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "test.txt", "text/plain", "Hello, World!".getBytes());
        int categoryId = 1;

        doNothing().when(ingestedDataService).processFile(file, categoryId);

        mockMvc.perform(MockMvcRequestBuilders.multipart("/filedata/upload")
                .file(file)
                .param("categoryId", String.valueOf(categoryId)))
                .andExpect(status().isOk())
                .andExpect(content().string("File uploaded and data processed successfully!"));
    }

    @Test
    public void testUploadFileInvalidCategory() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "test.txt", "text/plain", "Hello, World!".getBytes());
        int categoryId = 2;

        mockMvc.perform(MockMvcRequestBuilders.multipart("/filedata/upload")
                .file(file)
                .param("categoryId", String.valueOf(categoryId)))
                .andExpect(status().isOk())
                .andExpect(content().string("Category ID invalid"));
    }

    @Test
    public void testGetAllFileData() throws Exception {
        List<IngestedData> fileDataList = new ArrayList<>();
        // Add some test data to the list

        when(ingestedDataService.getAllFileData()).thenReturn(fileDataList);

        mockMvc.perform(MockMvcRequestBuilders.get("/filedata/getall"))
                .andExpect(status().isOk());
                // You can also check the response content if needed.
    }

    @Test
    public void testFindAllFiles() throws Exception {
        List<IngestedFiles> filesList = new ArrayList<>();
        // Add some test data to the list

        when(ingestedDataService.findAllFiles()).thenReturn(filesList);

        mockMvc.perform(MockMvcRequestBuilders.get("/filedata/files"))
                .andExpect(status().isOk());
                // You can also check the response content if needed.
    }
}

