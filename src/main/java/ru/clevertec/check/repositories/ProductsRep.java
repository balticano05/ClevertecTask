package ru.clevertec.check.repositories;

import ru.clevertec.check.entity.product.Product;
import ru.clevertec.check.entity.product.ProductOrder;
import ru.clevertec.check.mappers.DataMapperToProductMapper;
import ru.clevertec.check.utils.validator.FormatFileProducts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductsRep extends AbstractRepository<Product> {

    public ProductsRep() {
    }

    public void createRep(String pathToFile) throws IOException {

        super.createRep(pathToFile, "PRODUCT");
    }

    public List<ProductOrder> getAllProductsById(List<String> pairs) {

        List<ProductOrder> productOrdersList = new ArrayList<>();
        int id;
        int quantity;
        String[] values;

        for (String pair : pairs) {

            values = dividePair(pair);

            id = Integer.parseInt(values[0]);
            quantity = Integer.parseInt(values[1]);

            for (Product product : items) {

                if (product.getId() == id) {

                    System.out.println(quantity + " " + product.getQuantity());

                    productOrdersList.add(new ProductOrder(product, quantity));

                    break;
                }
            }
        }

        return productOrdersList;

    }

    @Override
    protected Boolean isValidFormatRepOfFile(List<String> data) {

        return FormatFileProducts.isValid(data);

    }

    @Override
    protected List<Product> getConvertedData(List<String> data) {

        DataMapperToProductMapper converterDataToProduct = new DataMapperToProductMapper();
        converterDataToProduct.convert(data);

        return converterDataToProduct.getConvertedData();

    }

    private String[] dividePair(String pair) {

        return pair.split("-");

    }
}