package com.backend.empowerpro.utils.fileOperations;

import org.springframework.context.annotation.Bean;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileDownloadUtil {
    @Bean
    public static ResponseEntity<InputStreamResource> downloadFile(String filePath) throws IOException {
        File file = new File(filePath);

        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        String mediaType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getName())
                .contentType(MediaType.parseMediaType(mediaType))
                .contentLength(file.length())
                .body(resource);
    }
}
