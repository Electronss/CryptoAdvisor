package com.company.cryptoadvisor.service;

import com.company.cryptoadvisor.convertor.CsvLine;
import com.company.cryptoadvisor.repository.CurrencyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FileParserServiceTest {

    @Autowired
    FileParserService service;

    @MockBean
    CurrencyRepository repository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void prossesFiles() {
    }


    @Test
    void convertToFiles() throws IOException {
        MockMultipartFile csvFile1 = new MockMultipartFile("file", new FileInputStream("./src/test/resources/incomingFiles/csv-currency/BTC_values.csv"));
        Object file = service.convertToFile(csvFile1);
        assertTrue(file != null);
        assertEquals(File.class, file.getClass());
    }

    @Test
    void parseCurrency() {
        File file = new File("./src/test/resources/incomingFiles/csv-currency/BTC_values.csv");
        List<CsvLine> list = service.parseCurrency(file);
        assertEquals(100, list.size());
        assertEquals(1641009600000l, list.get(0).getTimeStamp());
    }
}