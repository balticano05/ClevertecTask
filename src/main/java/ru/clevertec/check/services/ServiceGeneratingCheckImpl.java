package ru.clevertec.check.services;

import ru.clevertec.check.dto.RequestOrderDTO;
import ru.clevertec.check.entity.product.ProductOrder;
import ru.clevertec.check.entity.product.ProductOrders;
import ru.clevertec.check.entity.check.Check;
import ru.clevertec.check.entity.check.ProductsPriceDiscountInfo;
import ru.clevertec.check.entity.discount.DiscountCard;

import java.util.ArrayList;
import java.util.List;

public class ServiceGeneratingCheckImpl implements GeneratingCheckService<RequestOrderDTO, Check> {

    private Check check;

    @Override
    public Check execute(RequestOrderDTO requestOrder) {

        List<ProductsPriceDiscountInfo> productInfo = generateInfo(requestOrder.getProductOrders(),
                requestOrder.getDiscountCard().getDiscount());

        this.check = generateCheck(productInfo, requestOrder.getDebitCard().getBalance(), requestOrder.getDiscountCard());

        return check;
    }

    private List<ProductsPriceDiscountInfo> generateInfo(ProductOrders productOrders, int discount) {

        List<ProductsPriceDiscountInfo> productsPriceDiscountInfoList = new ArrayList<>();

        for (var el : productOrders.getProducts()) {

            int discountProduct = calculateProductDiscount(el, discount);

            ProductsPriceDiscountInfo productsPriceDiscountInfo = buildProductPriceDiscountInfo(el, discountProduct);

            productsPriceDiscountInfoList.add(productsPriceDiscountInfo);
        }

        return productsPriceDiscountInfoList;
    }

    private int calculateProductDiscount(ProductOrder el, int discount) {

        if (el.getProduct().isWholesale() && el.getQuantity() >= 5) {

            return 10;
        }

        return discount;
    }

    private ProductsPriceDiscountInfo buildProductPriceDiscountInfo(ProductOrder el, int discountProduct) {

        return new ProductsPriceDiscountInfo
                .ProductsPriceDiscountInfoBuilder(el.getQuantity())
                .setDescription(el.getProduct().getDescription())
                .setPrice(el.getProduct().getPrice())
                .setDiscount(el.getProduct().getPrice() * discountProduct * el.getQuantity() / 100)
                .setTotalPrice(el.getProduct().getPrice() * el.getQuantity())
                .build();

    }

    private Check generateCheck(List<ProductsPriceDiscountInfo> productInfo, Double balance, DiscountCard discountCard) {

        return new Check
                .CheckBuilder(discountCard.getCardNumber())
                .setDiscount(discountCard.getDiscount())
                .setTotalPrice(calculateTotalPrice(productInfo))
                .setTotalDiscount(calculateTotalDiscount(productInfo))
                .setTotalWithDiscount(calculateTotalWithDiscount(productInfo))
                .setProductsInfo(productInfo)
                .setUserBalance(balance)
                .build();

    }

    private Double calculateTotalPrice(List<ProductsPriceDiscountInfo> productInfo) {

        return productInfo.stream()
                .mapToDouble(ProductsPriceDiscountInfo::getTotalPrice)
                .sum();

    }

    private Double calculateTotalDiscount(List<ProductsPriceDiscountInfo> productInfo) {

        return productInfo.stream()
                .mapToDouble(ProductsPriceDiscountInfo::getDiscount)
                .sum();

    }

    private Double calculateTotalWithDiscount(List<ProductsPriceDiscountInfo> productInfo) {

        return calculateTotalPrice(productInfo) - calculateTotalDiscount(productInfo);
    }

}
