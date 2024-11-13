package com.tuta;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class InputReader {

    public static List<Email> readFilesToEmails(String directoryPath) throws IOException {
        List<Email> emails = new ArrayList<>();
        Path dirPath = Paths.get(directoryPath);

        if (!Files.isDirectory(dirPath)) {
            throw new IllegalArgumentException("The specified path is not a directory: " + directoryPath);
        }

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dirPath);) {
            for (Path filePath : stream) {
                if (Files.isRegularFile(filePath)) {
                    String fileName = filePath.getFileName().toString();
                    String content = new String(Files.readAllBytes(filePath));
                    Email email = new Email(content, fileName);

                    emails.add(email);
                }
            }
        } catch (IOException e) {
            System.out.println("Failed to read files from directory");
            e.printStackTrace();
        }

        return emails;
    }
}
