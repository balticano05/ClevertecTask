package ru.clevertec.check.services;

import java.io.IOException;

public interface IServiceDiscountCard<T> {

    void executeNumberDiscountCard(String numberDiscountCard) throws IOException;

}
