package ru.clevertec.check.utils.csv;

import ru.clevertec.check.dto.ResponseResultDto;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public final class FileWriterCSV {


    public static void writeInFile(ResponseResultDto responseResult, String saveToFile) throws IOException {

        String filePath = saveToFile;

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
