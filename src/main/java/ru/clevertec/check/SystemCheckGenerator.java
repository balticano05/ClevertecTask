package ru.clevertec.check;

import ru.clevertec.check.converter.ConverterCheckToData;
import ru.clevertec.check.dto.RequestOrderDTO;
import ru.clevertec.check.dto.ResponseParsedArgsDTO;
import ru.clevertec.check.dto.ResponseResultDTO;
import ru.clevertec.check.entity.check.Check;
import ru.clevertec.check.entity.debit.DebitCard;
import ru.clevertec.check.entity.discount.DiscountCard;
import ru.clevertec.check.entity.product.ProductOrders;
import ru.clevertec.check.services.ServiceDiscountCard;
import ru.clevertec.check.services.ServiceGeneratingCheck;
import ru.clevertec.check.services.ServiceParserArgs;
import ru.clevertec.check.services.ServiceProducts;
import ru.clevertec.check.utils.csv.FileWriterCSV;
import ru.clevertec.check.utils.validator.FormatArgs;

import java.io.IOException;
import java.util.List;

public class SystemCheckGenerator {

    private final FormatArgs formatArgs;
    private final ServiceGeneratingCheck serviceGeneratingCheck;
    private final ServiceParserArgs serviceParserArgs;
    private final ServiceProducts serviceProducts;
    private final ServiceDiscountCard serviceDiscountCard;
    private final FileWriterCSV fileWriterCSV;

    public SystemCheckGenerator() {
        this.formatArgs = new FormatArgs();
        this.serviceGeneratingCheck = new ServiceGeneratingCheck();
        this.serviceParserArgs = new ServiceParserArgs();
        this.serviceProducts = new ServiceProducts();
        this.serviceDiscountCard = new ServiceDiscountCard();
        this.fileWriterCSV = new FileWriterCSV();
    }

    public void generate(String args) {
        try {
            if (formatArgs.isValid(args)) {

                ResponseParsedArgsDTO responseParsedArgs = parseArguments(args);

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

        ConverterCheckToData converter = new ConverterCheckToData();
        converter.convert(check);

        ResponseResultDTO responseResultDTO;

        if (check.getTotalWithDiscount() <= debitCard.getBalance()) {

            responseResultDTO = new ResponseResultDTO(converter.getConvertedEntity());

            printChek(converter.getConvertedEntity());

            fileWriterCSV.writeInFile(responseResultDTO);
        } else {

            responseResultDTO = new ResponseResultDTO(List.of("ERROR", "NOT ENOUGH MONEY"));

            fileWriterCSV.writeInFile(responseResultDTO);
            System.out.println("BAD REQUEST.");
        }
    }

    private void handleError(String message) throws IOException {

        ResponseResultDTO responseResultDTO = new ResponseResultDTO(List.of("ERROR", message));

        fileWriterCSV.writeInFile(responseResultDTO);

        System.out.println("BAD REQUEST.");
    }

    private void printChek(List<String> data) {

        for (String el : data) {

            System.out.println(el);
        }
    }
}
