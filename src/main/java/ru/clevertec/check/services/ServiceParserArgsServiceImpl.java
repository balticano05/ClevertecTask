package ru.clevertec.check.services;

import ru.clevertec.check.dto.ResponseParsedArgsDTO;
import ru.clevertec.check.utils.systemconsts.PatternConst;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

public class ServiceParserArgsServiceImpl implements ParserArgsService {

    @Override
    public ResponseParsedArgsDTO parseArgs(String args) {

        ResponseParsedArgsDTO responseParsedArgs = new ResponseParsedArgsDTO();

        responseParsedArgs.setPairs(parsePairs(args));
        responseParsedArgs.setNumberDiscountCard(parseDiscountCard(args));
        responseParsedArgs.setBalance(parseBalance(args));

        return responseParsedArgs;

    }

    private List<String> parsePairs(String args) {
        List<String> pairs = new ArrayList<>();

        Matcher idQuantityMatcher = PatternConst.REGEX_PAIR.matcher(args);

        while (idQuantityMatcher.find()) {
            pairs.add(idQuantityMatcher.group());
        }

        return pairs;
    }

    private String parseDiscountCard(String args) {

        String discountCard = "none";

        Matcher discountCardMatcher = PatternConst.REGEX_DISCOUNT.matcher(args);
        if (discountCardMatcher.find()) {

            discountCard = discountCardMatcher.group(1);
        }

        return discountCard;
    }

    private Double parseBalance(String args) {
        Double balance = 0.0;

        Matcher balanceDebitCardMatcher = PatternConst.REGEX_BALANCE.matcher(args);
        if (balanceDebitCardMatcher.find()) {
            balance = Double.parseDouble(balanceDebitCardMatcher.group(1));
        }

        return balance;
    }

}
