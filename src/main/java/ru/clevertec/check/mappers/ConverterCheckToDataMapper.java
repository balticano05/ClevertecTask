package ru.clevertec.check.mappers;

import ru.clevertec.check.entity.check.Check;
import ru.clevertec.check.entity.check.ProductsPriceDiscountInfo;
import ru.clevertec.check.utils.systemconsts.StringConst;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ConverterCheckToDataMapper implements EntityMapper<Check> {

    private final List<String> data;

    public ConverterCheckToDataMapper() {
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

        SimpleDateFormat dateFormat = new SimpleDateFormat(StringConst.FORMAT_DATE_TIME);
        return dateFormat.format(date);

    }

    private void addProductsInfo(Check check) {

        DecimalFormat decimalFormat = new DecimalFormat(StringConst.FORMAT_DOUBLE_VALUES);

        for (ProductsPriceDiscountInfo product : check.getProducts()) {

            data.add(String.format(StringConst.FORMAT_INFO_ABOUT_PRODUCTS_IN_CHECK,
                    product.getQuantity(),
                    product.getDescription(),
                    decimalFormat.format(product.getPrice()),
                    decimalFormat.format(product.getDiscount()),
                    decimalFormat.format(product.getTotalPrice())));
        }
    }

    private void addDiscountCardInfo(Check check) {

        if (!check.getDiscountCardNumber().equals(StringConst.NONE)) {

            data.add("\n" + StringConst.DATA_CARD_DISCOUNT_PERCENTAGE);
            data.add(String.format(StringConst.FORMAT_CARD_DISCOUNT, check.getDiscountCardNumber(), formatDecimal(check.getDiscount())));
        }
    }

    private void addCheckPriceInfo(Check check) {

        data.add("\n" + StringConst.DATA_CHECK_PRICE);
        data.add(String.format(StringConst.FORMAT_CHECK_PRICE,
                formatDecimal(check.getTotalPrice()),
                formatDecimal(check.getTotalDiscount()),
                formatDecimal(check.getTotalWithDiscount())));
    }

    private String formatDecimal(double value) {

        DecimalFormat decimalFormat = new DecimalFormat(StringConst.FORMAT_DOUBLE_VALUES);

        return decimalFormat.format(value);
    }

    @Override
    public List<String> getConvertedEntity() {
        return this.data;
    }
}
