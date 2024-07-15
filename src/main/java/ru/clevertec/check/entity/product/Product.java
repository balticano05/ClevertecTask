package ru.clevertec.check.entity.product;

public class Product {

    private int id;
    private String description;
    private double price;
    private int quantity;
    private boolean isWholesale;

    public Product() {
    }

    public Product(int id, String description, double price, int quantity, boolean isWholesale) {
        this.id = id;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.isWholesale = isWholesale;
    }

    private Product(ProductBuilder builder) {
        this.id = builder.id;
        this.description = builder.description;
        this.price = builder.price;
        this.quantity = builder.quantity;
        this.isWholesale = builder.isWholesale;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean isWholesale() {
        return isWholesale;
    }

    public static class ProductBuilder {
        private int id;
        private String description;
        private double price;
        private int quantity;
        private boolean isWholesale;

        public ProductBuilder(int id) {
            this.id = id;
        }

        public ProductBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public ProductBuilder setPrice(double price) {
            this.price = price;
            return this;
        }

        public ProductBuilder setQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public ProductBuilder setWholesale(boolean isWholesale) {
            this.isWholesale = isWholesale;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", isWholesale=" + isWholesale +
                '}';
    }

}
