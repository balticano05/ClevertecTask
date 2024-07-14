package ru.clevertec.check.services;

import ru.clevertec.check.entity.discount.DiscountCard;
import ru.clevertec.check.repositories.DiscountCardRep;

import java.io.IOException;

public class ServiceDiscountCard implements IServiceDiscountCard {

    private DiscountCard discountCards = new DiscountCard();

    public void ServiceDiscountCard() {
    }

    public void executeNumberDiscountCard(String numberDiscountCard) throws IOException {

        DiscountCardRep discountCardRep = new DiscountCardRep();
        discountCardRep.createRep();

        this.discountCards = discountCardRep.getDiscountCardByCardNumber(numberDiscountCard);
    }

    public DiscountCard getDiscountCards() {

        return this.discountCards;
    }

}
