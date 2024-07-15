package ru.clevertec.check.services;

import java.io.IOException;

public interface DiscountCardService<T> {

    void executeNumberDiscountCard(String numberDiscountCard) throws IOException;

}
