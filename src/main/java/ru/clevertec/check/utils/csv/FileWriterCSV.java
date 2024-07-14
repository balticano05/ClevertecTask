package ru.clevertec.check.utils.csv;

import ru.clevertec.check.dto.ResponseResultDTO;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileWriterCSV {

    public FileWriterCSV() {
    }

    public void writeInFile(ResponseResultDTO responseResult) throws IOException {

        String filePath = "result.csv";
        if (Files.notExists(Paths.get(filePath))) {
            Files.createFile(Paths.get(filePath));
        }

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(Paths.get(filePath).toFile()))) {
            for (String line : responseResult.getData()) {
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }
        }
    }
}
