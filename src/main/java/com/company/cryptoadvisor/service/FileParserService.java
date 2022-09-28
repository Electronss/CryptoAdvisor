package com.company.cryptoadvisor.service;

import com.company.cryptoadvisor.convertor.CsvLine;
import com.company.cryptoadvisor.convertor.CurrencyConvertor;
import com.company.cryptoadvisor.convertor.CurrencyCsvLine;
import com.company.cryptoadvisor.repository.CurrencyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Slf4j
@Service
public class FileParserService extends AbstractFileService {

    private final CurrencyRepository repository;
    private final CurrencyConvertor convertor;

    public FileParserService(CurrencyRepository repository, CurrencyConvertor convertor) {
        this.repository = repository;
        this.convertor = convertor;
    }

    public void prossesFiles(List<MultipartFile> fileList) {
        fileList.forEach(file -> {
            File fileTemp = convertToFile(file);
            List<CsvLine> list = parseCurrency(fileTemp);
            if (list != null)
                repository.saveAll(convertor.convert(list));
        });
    }

    protected List<CsvLine> parseCurrency(File file) {
        try {
            return parseCsvFile(file, CurrencyCsvLine.class);
        } catch (Exception e) {
            log.error("An error occurs while parsing the CSV file: {}", file.getName());
            return null;
        }
    }



}
