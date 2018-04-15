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

    public List<String> getRows(MultipartFile file) throws IOException, FileException {
            String pathToFile = writeFile(file);
            return readFile(pathToFile);
    }

    private List<String> readFile(String csvFile) throws IOException {
        Files.lines(Paths.get(csvFile), StandardCharsets.UTF_8).forEach(row -> rows.add(row));
        if (Files.deleteIfExists(Paths.get(csvFile))) {
            return rows.isEmpty() ? null : rows;
        }
        return null;
    }

    private String writeFile(MultipartFile file) throws IOException, FileException {
        byte[] bytes = file.getBytes();
        String UPLOADED_FOLDER = "/home/denis/dev/JS Projects/forSpringMVCStore/SpringBoot/Angular5SpringBoot/src/main/tempfiles/";
        Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
        if (Files.isWritable(path)) {
            throw new FileException("File can't to save.", 1);
        }
        Files.write(path, bytes);
        // log.info("Get file: " + file.getOriginalFilename();
        return UPLOADED_FOLDER + file.getOriginalFilename();
    }
}