package ru.clevertec.check.entity.product;

import java.util.List;

public class ProductOrders {

    private List<ProductOrder> products;

    public ProductOrders(List<ProductOrder> products) {
        this.products = products;
    }

    public ProductOrders() {
    }

    public List<ProductOrder> getProducts() {
        return products;
    }

    public void setProducts(List<ProductOrder> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "ProductOrders{" +
                "products=" + products +
                '}';
    }

}
