package ru.clevertec.check.utils.validator;

import ru.clevertec.check.utils.systemconsts.PatternConst;
import ru.clevertec.check.utils.systemconsts.StringConst;

import java.util.List;

public final class FormatFileProducts {

    private FormatFileProducts() {
    }

    public static boolean isValid(List<String> data) {

        return FormatValidator.isValid(data, StringConst.FIRST_LINE_FILE_PRODUCT, PatternConst.REGEX_PRODUCT.pattern());
    }
}