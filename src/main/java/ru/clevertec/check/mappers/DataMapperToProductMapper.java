package ru.clevertec.check.mappers;

import ru.clevertec.check.entity.product.Product;

public class DataMapperToProductMapper extends AbstractDataMapper<Product> {

    @Override
    protected Product convertLineToEntity(String[] values) {

        return new Product.ProductBuilder(Integer.parseInt(values[0]))
                .setDescription(values[1])
                .setPrice(Double.parseDouble(values[2]))
                .setQuantity(Integer.parseInt(values[3]))
                .setWholesale(Boolean.parseBoolean(values[4]))
                .build();

    }
}
