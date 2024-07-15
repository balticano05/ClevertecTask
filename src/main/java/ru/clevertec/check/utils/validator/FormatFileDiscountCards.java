package ru.clevertec.check.utils.validator;

import ru.clevertec.check.utils.systemconsts.StringConst;
import ru.clevertec.check.utils.systemconsts.PatternConst;

import java.util.List;

public final class FormatFileDiscountCards {

    private FormatFileDiscountCards() {
    }

    public static boolean isValid(List<String> data) {

        return FormatValidator.isValid(data, StringConst.FIRST_LINE_FILE_DISCOUNT_CARD, PatternConst.REGEX_DISCOUNT_CARD.pattern());
    }
}
