package ru.clevertec.check.repositories;

import ru.clevertec.check.utils.csv.FileReaderCSV;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractRepository<T> {

    protected List<T> items = new ArrayList<>();

    public AbstractRepository() {
    }

    public void createRep(String filePath, String fileType) throws IOException {

        if (isValidFormatRepOfFile(getDataFromFile(filePath))) {

            this.items = getConvertedData(getDataFromFile(filePath));

        } else {
            System.out.println("FILE IS NOT OK.");

            throw new IOException("Invalid " + fileType + " file format");
        }
    }

    protected List<String> getDataFromFile(String filePath) throws IOException {

        FileReaderCSV fileReaderCSV = new FileReaderCSV();
        fileReaderCSV.readFile(filePath);

        return fileReaderCSV.getData();
    }

    protected abstract Boolean isValidFormatRepOfFile(List<String> data);

    protected abstract List<T> getConvertedData(List<String> data);

}