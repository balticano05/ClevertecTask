package ru.clevertec.check.services;

import ru.clevertec.check.dto.ResponseParsedArgsDto;
import ru.clevertec.check.mappers.ConverterCheckToDataMapper;
import ru.clevertec.check.dto.RequestOrderDto;
import ru.clevertec.check.dto.ResponseResultDto;
import ru.clevertec.check.entity.check.Check;
import ru.clevertec.check.entity.debit.DebitCard;
import ru.clevertec.check.entity.discount.DiscountCard;
import ru.clevertec.check.entity.product.ProductOrders;
import ru.clevertec.check.utils.csv.FileWriterCSV;
import ru.clevertec.check.utils.systemconsts.StringConst;
import ru.clevertec.check.utils.validator.FormatArgs;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class CheckGeneratorServiceImp implements CheckGeneratorService {

    private final ServiceGeneratingCheckImpl serviceGeneratingCheck;
    private final ServiceParserArgsServiceImpl serviceParserArgs;
    private final ServiceProductsServiceImpl serviceProducts;
    private final ServiceDiscountCardServiceImpl serviceDiscountCard;

    public CheckGeneratorServiceImp() {
        this.serviceGeneratingCheck = new ServiceGeneratingCheckImpl();
        this.serviceParserArgs = new ServiceParserArgsServiceImpl();
        this.serviceProducts = new ServiceProductsServiceImpl();
        this.serviceDiscountCard = new ServiceDiscountCardServiceImpl();
    }

    public void generate(String args) {
        try {
            if (FormatArgs.isValid(args)) {
                ResponseParsedArgsDto responseParsedArgs = parseArguments(args);
                String pathToFile = responseParsedArgs.getPathToFile();
                String saveToFile = responseParsedArgs.getSaveToFile();

                System.out.println(pathToFile + "\n" + saveToFile);
                handlePaths(pathToFile, saveToFile, responseParsedArgs);
            } else {
                handleError(StringConst.BAD_REQUEST, StringConst.FILE_RESULT);
            }
        } catch (Exception e) {
            handleErrorSafely(StringConst.INTERNAL_ERROR, StringConst.FILE_RESULT, e);
        }
    }

    private void handlePaths(String pathToFile, String saveToFile, ResponseParsedArgsDto responseParsedArgs) throws IOException {
        if (!StringConst.NONE.equals(pathToFile) && !StringConst.NONE.equals(saveToFile)) {
            processAndGenerateCheck(responseParsedArgs, saveToFile);
        } else {
            handleMissingPathOrSaveFile(pathToFile, saveToFile);
        }
    }

    private void processAndGenerateCheck(ResponseParsedArgsDto responseParsedArgs, String saveToFile) throws IOException {
        try {
            ProductOrders productOrders = processProducts(responseParsedArgs);
            printProducts(productOrders);

            DiscountCard discountCard = processDiscountCard(responseParsedArgs);
            System.out.println(discountCard);

            DebitCard debitCard = processDebitCard(responseParsedArgs);
            RequestOrderDto requestOrder = new RequestOrderDto(productOrders, discountCard, debitCard);

            Check check = serviceGeneratingCheck.execute(requestOrder);
            handleCheckResult(check, debitCard, saveToFile);

        } catch (IOException e) {
            handleError(StringConst.INTERNAL_ERROR, saveToFile);
        }
    }

    private void handleMissingPathOrSaveFile(String pathToFile, String saveToFile) throws IOException {
        if (StringConst.NONE.equals(pathToFile)) {
            handleError(StringConst.BAD_REQUEST, saveToFile.equals(StringConst.NONE) ? StringConst.FILE_RESULT : saveToFile);
        } else if (StringConst.NONE.equals(saveToFile)) {
            handleError(StringConst.BAD_REQUEST, StringConst.FILE_RESULT);
        }
    }

    private void handleErrorSafely(String errorMessage, String defaultFile, Exception e) {
        try {
            handleError(errorMessage, defaultFile);
        } catch (IOException ex) {
            e.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    private ResponseParsedArgsDto parseArguments(String args) throws IOException {
        return serviceParserArgs.parseArgs(args);
    }

    private ProductOrders processProducts(ResponseParsedArgsDto responseParsedArgs) throws IOException {
        String pathToFile = responseParsedArgs.getPathToFile();
        File file = new File(pathToFile);

        if (!file.exists()) {
            throw new IOException("File not found: " + pathToFile);
        }

        serviceProducts.takeOrders(responseParsedArgs.getPairs(), pathToFile);
        return serviceProducts.executeOrders();
    }

    private DiscountCard processDiscountCard(ResponseParsedArgsDto responseParsedArgs) throws IOException {
        serviceDiscountCard.executeNumberDiscountCard(responseParsedArgs.getNumberDiscountCard());
        return serviceDiscountCard.getDiscountCards();
    }

    private DebitCard processDebitCard(ResponseParsedArgsDto responseParsedArgs) {
        DebitCard debitCard = new DebitCard(responseParsedArgs.getBalance());
        System.out.println(debitCard);
        return debitCard;
    }

    private void printProducts(ProductOrders productOrders) {
        for (var product : productOrders.getProducts()) {
            System.out.println(product);
        }
    }

    private void handleCheckResult(Check check, DebitCard debitCard, String saveToFile) throws IOException {
        ConverterCheckToDataMapper converter = new ConverterCheckToDataMapper();
        converter.convert(check);

        ResponseResultDto responseResultDTO;

        if (check.getTotalWithDiscount() <= debitCard.getBalance()) {
            responseResultDTO = new ResponseResultDto(converter.getConvertedEntity());
            printCheck(converter.getConvertedEntity());
            FileWriterCSV.writeInFile(responseResultDTO, saveToFile);
        } else {
            responseResultDTO = new ResponseResultDto(List.of(StringConst.ERROR, StringConst.NOT_ENOUGH_MONEY));
            FileWriterCSV.writeInFile(responseResultDTO, saveToFile);
            System.out.println(StringConst.BAD_REQUEST);
        }
    }

    private void handleError(String message, String pathToFile) throws IOException {
        ResponseResultDto responseResultDTO = new ResponseResultDto(List.of(StringConst.ERROR, message));
        FileWriterCSV.writeInFile(responseResultDTO, pathToFile);
        System.out.println(StringConst.BAD_REQUEST);
    }

    private void printCheck(List<String> data) {
        for (String el : data) {
            System.out.println(el);
        }
    }
}
