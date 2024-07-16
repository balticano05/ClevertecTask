package ru.clevertec.check.dto;

import ru.clevertec.check.entity.debit.DebitCard;
import ru.clevertec.check.entity.discount.DiscountCard;
import ru.clevertec.check.entity.product.ProductOrders;

public class RequestOrderDto {

    private ProductOrders productOrders;
    private DiscountCard discountCard;
    private DebitCard debitCard;

    public RequestOrderDto() {
    }

    public RequestOrderDto(ProductOrders productOrders, DiscountCard discountCard, DebitCard debitCard) {
        this.productOrders = productOrders;
        this.discountCard = discountCard;
        this.debitCard = debitCard;
    }

    public ProductOrders getProductOrders() {
        return productOrders;
    }

    public void setProductOrders(ProductOrders productOrders) {
        this.productOrders = productOrders;
    }

    public DiscountCard getDiscountCard() {
        return discountCard;
    }

    public void setDiscountCard(DiscountCard discountCard) {
        this.discountCard = discountCard;
    }

    public DebitCard getDebitCard() {
        return debitCard;
    }

    public void setDebitCard(DebitCard debitCard) {
        this.debitCard = debitCard;
    }
}
