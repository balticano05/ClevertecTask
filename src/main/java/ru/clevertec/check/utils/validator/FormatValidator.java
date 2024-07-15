package ru.clevertec.check.utils.validator;

import java.util.List;

public final class FormatValidator {

    private FormatValidator() {
    }

    public static boolean isValid(List<String> data, String firstLinePattern, String linePattern) {
        if (data == null || data.isEmpty()) {
            return false;
        }

        if (!isValidFirstLine(data.get(0), firstLinePattern)) {
            return false;
        }

        for (int i = 1; i < data.size(); i++) {
            if (!isValidLine(data.get(i), linePattern)) {
                return false;
            }
        }

        return true;
    }

    public static boolean isValidFirstLine(String line, String expectedValue) {
        if (line == null || line.isEmpty()) {
            return false;
        }
        line = line.trim().replace(" ", "");
        return line.equals(expectedValue);
    }

    public static boolean isValidLine(String line, String regex) {
        if (line == null) {
            return false;
        }
        return line.matches(regex);
    }
}

