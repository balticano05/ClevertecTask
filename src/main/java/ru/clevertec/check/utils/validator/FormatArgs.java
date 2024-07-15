package ru.clevertec.check.utils.validator;

import ru.clevertec.check.utils.systemconsts.PatternConst;

import java.util.regex.Matcher;

public final class FormatArgs {

    public static boolean isValid(String args) {

        Matcher matcher = PatternConst.REGEX_ARGS.matcher(args);

        return matcher.matches();

    }
}
