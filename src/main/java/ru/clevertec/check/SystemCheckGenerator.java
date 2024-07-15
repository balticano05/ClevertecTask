package ru.clevertec.check;

import ru.clevertec.check.mappers.ConverterCheckToDataMapper;
import ru.clevertec.check.dto.RequestOrderDTO;
import ru.clevertec.check.dto.ResponseParsedArgsDTO;
import ru.clevertec.check.dto.ResponseResultDTO;
import ru.clevertec.check.entity.check.Check;
import ru.clevertec.check.entity.debit.DebitCard;
import ru.clevertec.check.entity.discount.DiscountCard;
import ru.clevertec.check.entity.product.ProductOrders;
import ru.clevertec.check.services.ServiceDiscountCardServiceImpl;
import ru.clevertec.check.services.ServiceGeneratingCheckImpl;
import ru.clevertec.check.services.ServiceParserArgsServiceImpl;
import ru.clevertec.check.services.ServiceProductsServiceImpl;
import ru.clevertec.check.utils.csv.FileWriterCSV;
import ru.clevertec.check.utils.validator.FormatArgs;

import java.io.IOException;
import java.util.List;

public class SystemCheckGenerator {

    private final ServiceGeneratingCheckImpl serviceGeneratingCheck;
    private final ServiceParserArgsServiceImpl serviceParserArgs;
    private final ServiceProductsServiceImpl serviceProducts;
    private final ServiceDiscountCardServiceImpl serviceDiscountCard;

    public SystemCheckGenerator() {
        this.serviceGeneratingCheck = new ServiceGeneratingCheckImpl();
        this.serviceParserArgs = new ServiceParserArgsServiceImpl();
        this.serviceProducts = new ServiceProductsServiceImpl();
        this.serviceDiscountCard = new ServiceDiscountCardServiceImpl();
    }

    public void generate(String args) {
        try {
            if (FormatArgs.isValid(args)) {

                ResponseParsedArgsDTO responseParsedArgs = parseArguments(args);

                System.out.println(responseParsedArgs.getPathToFile()+"\n"+responseParsedArgs.getSaveToFile());

                ProductOrders productOrders = processProducts(responseParsedArgs);
                printProducts(productOrders);

                DiscountCard discountCard = processDiscountCard(responseParsedArgs);
                System.out.println(discountCard);

                DebitCard debitCard = processDebitCard(responseParsedArgs);
                RequestOrderDTO requestOrder = new RequestOrderDTO(productOrders, discountCard, debitCard);

                Check check = serviceGeneratingCheck.execute(requestOrder);
                handleCheckResult(check, debitCard);

            } else {
                handleError("BAD REQUEST");
            }
        } catch (Exception e) {

            try {

                handleError("INTERNAL ERROR");
            } catch (IOException ex) {

                e.printStackTrace();
                throw new RuntimeException(ex);
            }
        }
    }

    private ResponseParsedArgsDTO parseArguments(String args) throws IOException {
        return serviceParserArgs.parseArgs(args);
    }

    private ProductOrders processProducts(ResponseParsedArgsDTO responseParsedArgs) throws IOException {

        serviceProducts.takeOrders(responseParsedArgs.getPairs());

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

    private void handleCheckResult(Check check, DebitCard debitCard) throws IOException {

        ConverterCheckToDataMapper converter = new ConverterCheckToDataMapper();
        converter.convert(check);

        ResponseResultDTO responseResultDTO;

        if (check.getTotalWithDiscount() <= debitCard.getBalance()) {

            responseResultDTO = new ResponseResultDTO(converter.getConvertedEntity());

            printChek(converter.getConvertedEntity());

            FileWriterCSV.writeInFile(responseResultDTO);
        } else {

            responseResultDTO = new ResponseResultDTO(List.of("ERROR", "NOT ENOUGH MONEY"));

            FileWriterCSV.writeInFile(responseResultDTO);
            System.out.println("BAD REQUEST.");
        }
    }

    private void handleError(String message) throws IOException {

        ResponseResultDTO responseResultDTO = new ResponseResultDTO(List.of("ERROR", message));

        FileWriterCSV.writeInFile(responseResultDTO);

        System.out.println("BAD REQUEST.");
    }

    private void printChek(List<String> data) {

        for (String el : data) {

            System.out.println(el);
        }
    }
}
