package ru.clevertec.check.converter;

import ru.clevertec.check.entity.check.Check;
import ru.clevertec.check.entity.check.ProductsPriceDiscountInfo;
import ru.clevertec.check.utils.systemconsts.StringConst;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ConverterCheckToData implements IConverterEntity<Check> {

    private final List<String> data;

    public ConverterCheckToData() {
        this.data = new ArrayList<>();
    }

    @Override
    public void convert(Check check) {

        addHeader();
        addProductsInfo(check);
        addDiscountCardInfo(check);
        addCheckPriceInfo(check);
    }

    private void addHeader() {

        data.add(StringConst.DATE_SEMICOLON_TIME);

        String formattedDate = formatDate(new Date());
        data.add(formattedDate + "\n");

        data.add(StringConst.DATA_INFO_ABOUT_PRODUCTS);
    }

    private String formatDate(Date date) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy;HH:mm:ss");
        return dateFormat.format(date);

    }

    private void addProductsInfo(Check check) {

        DecimalFormat decimalFormat = new DecimalFormat("#.##");

        for (ProductsPriceDiscountInfo product : check.getProducts()) {

            data.add(String.format("%s;%s;%s$;%s$;%s$",
                    product.getQuantity(),
                    product.getDescription(),
                    decimalFormat.format(product.getPrice()),
                    decimalFormat.format(product.getDiscount()),
                    decimalFormat.format(product.getTotalPrice())));
        }
    }

    private void addDiscountCardInfo(Check check) {

        if (!check.getDiscountCardNumber().equals("none")) {

            data.add("\n" + StringConst.DATA_CARD_DISCOUNT_PERCENTAGE);
            data.add(String.format("%s;%s%%", check.getDiscountCardNumber(), formatDecimal(check.getDiscount())));
        }
    }

    private void addCheckPriceInfo(Check check) {

        data.add("\n" + StringConst.DATA_CHECK_PRICE);
        data.add(String.format("%s$;%s$;%s$",
                formatDecimal(check.getTotalPrice()),
                formatDecimal(check.getTotalDiscount()),
                formatDecimal(check.getTotalWithDiscount())));
    }

    private String formatDecimal(double value) {

        DecimalFormat decimalFormat = new DecimalFormat("#.##");

        return decimalFormat.format(value);
    }

    @Override
    public List<String> getConvertedEntity() {
        return this.data;
    }
}
