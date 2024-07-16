package ru.clevertec.check.services;

import ru.clevertec.check.mappers.ConverterCheckToDataMapper;
import ru.clevertec.check.dto.RequestOrderDTO;
import ru.clevertec.check.dto.ResponseParsedArgsDTO;
import ru.clevertec.check.dto.ResponseResultDTO;
import ru.clevertec.check.entity.check.Check;
import ru.clevertec.check.entity.debit.DebitCard;
import ru.clevertec.check.entity.discount.DiscountCard;
import ru.clevertec.check.entity.product.ProductOrders;
import ru.clevertec.check.utils.csv.FileWriterCSV;
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

                ResponseParsedArgsDTO responseParsedArgs = parseArguments(args);

                String pathToFile = responseParsedArgs.getPathToFile();
                String saveToFile = responseParsedArgs.getSaveToFile();

                System.out.println(pathToFile + "\n" + saveToFile);

                if (!"none".equals(pathToFile) && !"none".equals(saveToFile)) {

                    processAndGenerateCheck(responseParsedArgs, saveToFile);
                } else {

                    handleMissingPathOrSaveFile(pathToFile, saveToFile);
                }
            } else {

                handleError("BAD REQUEST", "result.csv");
            }
        } catch (Exception e) {

            handleErrorSafely("INTERNAL ERROR", "result.csv", e);
        }
    }

    private void processAndGenerateCheck(ResponseParsedArgsDTO responseParsedArgs, String saveToFile) throws IOException {

        try {

            ProductOrders productOrders = processProducts(responseParsedArgs);
            printProducts(productOrders);

            DiscountCard discountCard = processDiscountCard(responseParsedArgs);
            System.out.println(discountCard);

            DebitCard debitCard = processDebitCard(responseParsedArgs);
            RequestOrderDTO requestOrder = new RequestOrderDTO(productOrders, discountCard, debitCard);

            Check check = serviceGeneratingCheck.execute(requestOrder);
            handleCheckResult(check, debitCard, saveToFile);

        } catch (IOException e) {

            handleError("INTERNAL ERROR", saveToFile);
        }
    }

    private void handleMissingPathOrSaveFile(String pathToFile, String saveToFile) throws IOException {

        if ("none".equals(pathToFile)) {

            handleError("BAD REQUEST", saveToFile.equals("none") ? "result.csv" : saveToFile);

        } else if ("none".equals(saveToFile)) {

            handleError("BAD REQUEST", "result.csv");
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

    private ResponseParsedArgsDTO parseArguments(String args) throws IOException {

        return serviceParserArgs.parseArgs(args);
    }

    private ProductOrders processProducts(ResponseParsedArgsDTO responseParsedArgs) throws IOException {

        String pathToFile = responseParsedArgs.getPathToFile();
        File file = new File(pathToFile);

        if (!file.exists()) {
            throw new IOException("File not found: " + pathToFile);
        }

        serviceProducts.takeOrders(responseParsedArgs.getPairs(), pathToFile);

        return serviceProducts.executeOrders();
    }

    private DiscountCard processDiscountCard(ResponseParsedArgsDTO responseParsedArgs) throws IOException {

        serviceDiscountCard.executeNumberDiscountCard(responseParsedArgs.getNumberDiscountCard());

        return serviceDiscountCard.getDiscountCards();
    }

    private DebitCard processDebitCard(ResponseParsedArgsDTO responseParsedArgs) {

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

        ResponseResultDTO responseResultDTO;

        if (check.getTotalWithDiscount() <= debitCard.getBalance()) {

            responseResultDTO = new ResponseResultDTO(converter.getConvertedEntity());
            printChek(converter.getConvertedEntity());

            FileWriterCSV.writeInFile(responseResultDTO, saveToFile);

        } else {

            responseResultDTO = new ResponseResultDTO(List.of("ERROR", "NOT ENOUGH MONEY"));
            FileWriterCSV.writeInFile(responseResultDTO, saveToFile);

            System.out.println("BAD REQUEST.");
        }
    }

    private void handleError(String message, String pathToFile) throws IOException {

        ResponseResultDTO responseResultDTO = new ResponseResultDTO(List.of("ERROR", message));
        FileWriterCSV.writeInFile(responseResultDTO, pathToFile);

        System.out.println("BAD REQUEST.");
    }

    private void printChek(List<String> data) {

        for (String el : data) {
            System.out.println(el);
        }
    }
}
