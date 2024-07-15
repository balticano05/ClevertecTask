package ru.clevertec.check.mappers;

import ru.clevertec.check.entity.discount.DiscountCard;

public class DataMapperToDiscountCardMapper extends AbstractDataMapper {

    @Override
    protected DiscountCard convertLineToEntity(String[] values) {

        return new DiscountCard.DiscountCardBuilder(Integer.parseInt(values[0]))
                .setCardNumber(values[1])
                .setDiscount(Integer.parseInt(values[2]))
                .build();

    }
}
