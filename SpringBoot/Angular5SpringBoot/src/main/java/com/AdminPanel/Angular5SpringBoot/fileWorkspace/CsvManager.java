package com.AdminPanel.Angular5SpringBoot.fileWorkspace;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CsvManager {

    private List<String> rows = new ArrayList<>();

    public List<String> getRows(MultipartFile file) {
        try {
            String pathToFile = writeFile(file);
            return readFile(pathToFile);
        } catch (IOException e) {
//            log.error("File read/write error" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    private List<String> readFile(String csvFile) throws IOException {
        Files.lines(Paths.get(csvFile), StandardCharsets.UTF_8).forEach(row -> rows.add(row));
        if (Files.deleteIfExists(Paths.get(csvFile))) {
            return rows.isEmpty() ? null : rows;
        }
        return null;
    }

    private String writeFile(MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        String UPLOADED_FOLDER = "/home/denis/dev/JS Projects/forSpringMVCStore/SpringBoot/Angular5SpringBoot/src/main/tempfiles/";
        Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
        Files.write(path, bytes);
        // log.info("Get file: " + file.getOriginalFilename();
        return UPLOADED_FOLDER + file.getOriginalFilename();
    }
}