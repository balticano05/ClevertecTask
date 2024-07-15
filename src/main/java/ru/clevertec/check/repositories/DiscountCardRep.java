package ru.clevertec.check.repositories;

import ru.clevertec.check.entity.discount.DiscountCard;
import ru.clevertec.check.mappers.DataMapperToDiscountCardMapper;
import ru.clevertec.check.utils.validator.FormatFileDiscountCards;

import java.io.IOException;
import java.util.List;

public class DiscountCardRep extends AbstractRepository<DiscountCard> {

    public DiscountCardRep() {
    }

    public void createRep() throws IOException {

        super.createRep("src/main/resources/discountCards.csv", "DISCOUNT CARDS");
    }

    public DiscountCard getDiscountCardByCardNumber(String cardNumber) {

        if (cardNumber.equals("none")) {

            return createDiscountCard(cardNumber, 0);
        }

        for (DiscountCard card : items) {

            if (card.getCardNumber().equals(cardNumber)) {

                return card;
            }
        }

        return createDiscountCard(cardNumber, 3);
    }

    private DiscountCard createDiscountCard(String cardNumber, int discount) {

        return new DiscountCard.DiscountCardBuilder(0)
                .setCardNumber(cardNumber)
                .setDiscount(discount)
                .build();

    }

    @Override
    protected Boolean isValidFormatRepOfFile(List<String> data) {

        return FormatFileDiscountCards.isValid(data);
    }

    @Override
    protected List<DiscountCard> getConvertedData(List<String> data) {

        DataMapperToDiscountCardMapper converterDataToDiscountCard = new DataMapperToDiscountCardMapper();
        converterDataToDiscountCard.convert(data);

        return converterDataToDiscountCard.getConvertedData();
    }
}
