package ru.clevertec.check.utils.csv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public final class FileReaderCSV {

    private FileReaderCSV() {
    }

    public static List<String> readFile(String filePath) throws IOException {

        List<String> data = new ArrayList<>();

        if (FileCheckerPath.isFileExists(filePath)) {

            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(Paths.get(filePath).toFile()))) {

                String line;

                while ((line = bufferedReader.readLine()) != null) {

                    data.add(line);
                }
            }
        } else {
            System.out.println("File not found: " + filePath);
        }

        return data;
    }
}


