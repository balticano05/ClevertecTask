package ru.clevertec.check.services;

import ru.clevertec.check.entity.product.ProductOrders;

import java.io.IOException;
import java.util.List;

public interface IServiceProducts {

    public void takeOrders(List<String> pairs) throws IOException;

    public ProductOrders executeOrders();

}
