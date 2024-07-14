package ru.clevertec.check.utils.validator;

import ru.clevertec.check.utils.systemconsts.PatternConst;
import ru.clevertec.check.utils.systemconsts.StringConst;

public class FormatFileProducts extends AbstractFormatValidator {

    @Override
    protected boolean isValidFirstLine(String line) {
        if (line == null || line.isEmpty()) {
            return false;
        }

        line = line.trim().replace(" ", "");

        return line.equals(StringConst.FIRST_LINE_FILE_PRODUCT);
    }

    @Override
    protected boolean isValidLine(String line) {
        if (line == null) {
            return false;
        }

        return PatternConst.REGEX_PRODUCT.matcher(line).matches();
    }
}