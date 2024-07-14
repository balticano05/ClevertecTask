package ru.clevertec.check.utils.csv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileReaderCSV {

    private List<String> data = new ArrayList<>();

    public FileReaderCSV() {
    }

    public void readFile(String filePath) throws IOException {

        if (FileCheckerPath.isFileExists(filePath)) {

            List<String> data = new ArrayList<>();
            BufferedReader bufferedReader = new BufferedReader(new FileReader(Paths.get(filePath).toFile()));

            String line;
            while ((line = bufferedReader.readLine()) != null) {

                data.add(line);
            }

            bufferedReader.close();

            this.data = data;

        } else {
            System.out.println("File products not found: " + filePath);
        }

    }

    public List<String> getData() {
        return data;
    }

}


