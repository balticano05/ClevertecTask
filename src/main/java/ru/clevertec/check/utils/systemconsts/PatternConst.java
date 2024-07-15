package ru.clevertec.check.utils.systemconsts;

import java.util.regex.Pattern;

public final class PatternConst {

    public static final Pattern REGEX_ARGS = Pattern.compile("^(\\d{1,7}-\\d{1,7}\\s+)+"
            + "(discountCard=\\d{4}\\s+)?"
            + "balanceDebitCard=\\d{1,7}(\\.\\d{1,2})?\\s*"
            + "(pathToFile=[^\\s]+\\.csv\\s*)?"
            + "(saveToFile=[^\\s]+\\.csv)?$");


    public static final Pattern REGEX_PAIR = Pattern.compile("\\d{1,7}-\\d{1,7}");

    public static final Pattern REGEX_DISCOUNT = Pattern.compile("discountCard=(\\d{4})");

    public static final Pattern REGEX_BALANCE = Pattern.compile("balanceDebitCard=(\\d{1,7}(\\.\\d{1,2})?)");

    public static final Pattern REGEX_PRODUCT = Pattern.compile("^(\\d{1,7});([^;]+);(\\d{1,7}(\\.\\d{1,2})?);" +
            "(\\d{1,7});(true|false)$");

    public static final Pattern REGEX_DISCOUNT_CARD = Pattern.compile("^(\\d{1,7});(\\d{4});([1-9]|[1-9]\\d|100)$");

    public static final Pattern REGEX_PATH_TO_FILE = Pattern.compile("pathToFile=([^\\s]+\\.csv)");

    public static final Pattern REGEX_SAVE_TO_FILE = Pattern.compile("saveToFile=([^\\s]+\\.csv)");
}
