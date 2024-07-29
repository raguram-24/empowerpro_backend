package com.backend.empowerpro.utils.fileOperations;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

public class FileUploadUtil {
    public static String uploadFile(String uploadDir, MultipartFile file) throws IOException {
        // Create the upload directory if it does not exist
        File uploadDirectory = new File(uploadDir);
        if (!uploadDirectory.exists()) {
            uploadDirectory.mkdirs();
        }

        // Generate a unique file name to avoid conflicts
        String originalFileName = file.getOriginalFilename();
        if (originalFileName == null || originalFileName.isEmpty()) {
            throw new IOException("Invalid file name.");
        }
        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String uniqueFileName = UUID.randomUUID().toString() + fileExtension;

        // Save the file to the upload directory
        Path filePath = Paths.get(uploadDir, uniqueFileName);
        try (var inputStream = file.getInputStream()) {
            Files.copy(inputStream, filePath);
        }

        // Return the relative path to the saved file
        return filePath.toString();
    }
}
