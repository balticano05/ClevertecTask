package ru.clevertec.check.utils.validator;

import java.util.List;

public abstract class AbstractFormatValidator {

    public boolean isValid(List<String> data) {
        if (data == null || data.isEmpty()) {
            return false;
        }

        if (!isValidFirstLine(data.get(0))) {
            return false;
        }

        for (int i = 1; i < data.size(); i++) {
            if (!isValidLine(data.get(i))) {
                return false;
            }
        }

        return true;
    }

    protected abstract boolean isValidFirstLine(String line);

    protected abstract boolean isValidLine(String line);

}
