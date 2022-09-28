package com.company.cryptoadvisor.controller;

import com.company.cryptoadvisor.service.FileParserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@RestController
@ApiOperation("File Upload")
@RequestMapping(value = "api/files")
public class FileUploadController extends AbstractController {

    private final String ALLOWED_FILE_EXTENSION = "csv";
    FileParserService service;

    public FileUploadController(FileParserService service) {
        this.service = service;
    }

    /**
     * TODO
     * @param files
     * @return
     */
    @PostMapping(path = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> uploadFile(@RequestParam("file") MultipartFile[] files) {
        List<MultipartFile> listFiles = Arrays.stream(files)
                .filter(this::isAllowedFileType)
                .collect(Collectors.toList());
        if (listFiles.isEmpty())
            return generateResponse("Excel files were not uploaded.");

        try {
            service.prossesFiles(listFiles);
            return generateResponse("Files were successfully uploaded.");
        } catch (Exception e) {
            log.error("Something was wrong. ", e);
            return generateBadResponse("Something was wrong");
        }
    }

    private boolean isAllowedFileType(MultipartFile f) {
        String fileName = Objects.requireNonNull(f.getOriginalFilename()).toLowerCase().trim();
        String fileExtension = FilenameUtils.getExtension(fileName);
        return fileExtension.equals(ALLOWED_FILE_EXTENSION);
    }

}
