package ru.clevertec.check.services;

import ru.clevertec.check.entity.product.ProductOrders;

import java.io.IOException;
import java.util.List;

public interface ProductsService {

    public void takeOrders(List<String> pairs,String pathToFile) throws IOException;

    public ProductOrders executeOrders();

}
