package com.company.cryptoadvisor.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.FileInputStream;

import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(FileUploadController.class)
class FileUploadControllerTest {

    private MockMvc mockMvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    @MockBean
    private FileUploadController controller;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .alwaysDo(MockMvcResultHandlers.print())
                .build();
    }

    @Test
    void uploadFile() throws Exception {
        MockMultipartFile csvFile1 = new MockMultipartFile("file", new FileInputStream("./src/test/resources/incomingFiles/csv-currency/BTC_values.csv"));

        when(controller.uploadFile(Mockito.any()))
                .thenReturn(ResponseEntity.ok().body(ResponseMessage.builder()
                        .messege("Files were successfully uploaded.")
                        .responce(null)
                        .build()));

        mockMvc.perform(multipart("/api/files/upload").file(csvFile1))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"messege\":\"Files were successfully uploaded.\",\"responce\":null}"));;
    }

    @Test
    void uploadFileWithWrongExtension() throws Exception {
        MockMultipartFile csvFile1 = new MockMultipartFile("file", new FileInputStream("./src/test/resources/incomingFiles/csv-currency/wrong_file_extension.csvd"));

        when(controller.uploadFile(Mockito.any()))
                .thenReturn(ResponseEntity.ok().body(ResponseMessage.builder()
                        .messege("Excel files were not uploaded.")
                                .responce(null)
                        .build()));

        mockMvc.perform(multipart("/api/files/upload").file(csvFile1))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"messege\":\"Excel files were not uploaded.\",\"responce\":null}"));
    }

}