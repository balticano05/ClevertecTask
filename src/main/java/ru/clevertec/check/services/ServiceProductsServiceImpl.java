package ru.clevertec.check.services;

import ru.clevertec.check.entity.product.ProductOrder;
import ru.clevertec.check.entity.product.ProductOrders;
import ru.clevertec.check.repositories.ProductsRep;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ServiceProductsServiceImpl implements ProductsService {

    public List<ProductOrder> productOrderList = new ArrayList<>();

    @Override
    public void takeOrders(List<String> pairs) throws IOException {

        ProductsRep productsRep = new ProductsRep();
        productsRep.createRep();

        this.productOrderList = productsRep.getAllProductsById(pairs);
    }

    @Override
    public ProductOrders executeOrders() {

        ProductOrders productsOrders = new ProductOrders();

        if (productOrderList.size() > 0) {

            for (ProductOrder productOrder : productOrderList) {

                if (!(productOrder.getProduct().getQuantity() >= productOrder.getQuantity())) {

                    productOrder.setQuantity(productOrder.getProduct().getQuantity());

                }
            }
        }

        productsOrders.setProducts(this.productOrderList);

        return productsOrders;
    }
}
