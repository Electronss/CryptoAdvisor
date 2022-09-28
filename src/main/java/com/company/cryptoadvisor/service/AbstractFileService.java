package com.company.cryptoadvisor.service;

import com.company.cryptoadvisor.convertor.CsvLine;
import com.company.cryptoadvisor.pojos.CsvTransfer;
import com.company.cryptoadvisor.utils.FileParserUtils;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.List;

public abstract class AbstractFileService {

    protected File convertToFile(MultipartFile file) {
        return FileParserUtils.convert(file);
    }

    protected List<CsvLine> parseCsvFile(File csvFile, Class<? extends CsvLine> clazz) throws Exception {
        CsvTransfer csvTransfer = new CsvTransfer();
        try (Reader reader = new FileReader(csvFile)) {
            CsvToBean<CsvLine> cb = new CsvToBeanBuilder<CsvLine>(reader)
                    .withType(clazz)
                    .build();
            csvTransfer.setCsvList(cb.parse());
        }
        return csvTransfer.getCsvList();
    }
}
