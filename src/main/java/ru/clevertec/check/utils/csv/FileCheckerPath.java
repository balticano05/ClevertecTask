package ru.clevertec.check.utils.csv;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileCheckerPath {

    public static boolean isFileExists(String filePath) {

        Path path = Paths.get(filePath);

        return Files.exists(path);
    }

}
