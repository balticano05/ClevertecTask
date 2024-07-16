package ru.clevertec.check.entity.check;

public class ProductsPriceDiscountInfo {

    private int quantity;
    private String description;
    private Double price;
    private Double discount;
    private Double totalPrice;

    public ProductsPriceDiscountInfo() {
    }

    public ProductsPriceDiscountInfo(int quantity, String description,
                                     Double price, Double discount, Double totalPrice) {
        this.quantity = quantity;
        this.description = description;
        this.price = price;
        this.discount = discount;
        this.totalPrice = totalPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public Double getDiscount() {
        return discount;
    }

    private ProductsPriceDiscountInfo(ProductsPriceDiscountInfoBuilder builder) {
        this.quantity = builder.quantity;
        this.description = builder.description;
        this.price = builder.price;
        this.discount = builder.discount;
        this.totalPrice = builder.totalPrice;
    }

    public static class ProductsPriceDiscountInfoBuilder {
        private int quantity;
        private String description;
        private Double price;
        private Double discount;
        private Double totalPrice;

        public ProductsPriceDiscountInfoBuilder(int quantity) {
            this.quantity = quantity;
        }

        public ProductsPriceDiscountInfoBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public ProductsPriceDiscountInfoBuilder setPrice(Double price) {
            this.price = price;
            return this;
        }

        public ProductsPriceDiscountInfoBuilder setDiscount(Double discount) {
            this.discount = discount;
            return this;
        }

        public ProductsPriceDiscountInfoBuilder setTotalPrice(Double totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

        public ProductsPriceDiscountInfo build() {
            return new ProductsPriceDiscountInfo(this);
        }
    }

    @Override
    public String toString() {
        return "ProductsPriceDiscountInfo{" +
                "quantity=" + quantity +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", discount=" + discount +
                ", totalPrice=" + totalPrice +
                '}';
    }

}
