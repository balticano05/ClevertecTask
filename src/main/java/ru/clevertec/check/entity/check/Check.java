package ru.clevertec.check.entity.check;

import java.util.List;

public class Check {

    private String discountCardNumber;
    private int discount;
    private Double totalPrice;
    private Double totalDiscount;
    private Double totalWithDiscount;
    private List<ProductsPriceDiscountInfo> productsInfo;
    private Double userBalance;

    public Check() {
    }

    public Check(String discountCardNumber, int discount, Double totalPrice, Double totalDiscount,
                 Double totalWithDiscount, List<ProductsPriceDiscountInfo> productsInfo, Double userBalance) {
        this.discountCardNumber = discountCardNumber;
        this.discount = discount;
        this.totalPrice = totalPrice;
        this.totalDiscount = totalDiscount;
        this.totalWithDiscount = totalWithDiscount;
        this.productsInfo = productsInfo;
        this.userBalance = userBalance;
    }

    public String getDiscountCardNumber() {
        return discountCardNumber;
    }

    public int getDiscount() {
        return discount;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public Double getTotalDiscount() {
        return totalDiscount;
    }

    public Double getTotalWithDiscount() {
        return totalWithDiscount;
    }

    public Double getUserBalance() {
        return userBalance;
    }

    public List<ProductsPriceDiscountInfo> getProducts() {
        return productsInfo;
    }

    private Check(CheckBuilder builder) {
        this.discountCardNumber = builder.discountCardNumber;
        this.discount = builder.discount;
        this.totalPrice = builder.totalPrice;
        this.totalDiscount = builder.totalDiscount;
        this.totalWithDiscount = builder.totalWithDiscount;
        this.productsInfo = builder.productsInfo;
        this.userBalance = builder.userBalance;
    }

    public static class CheckBuilder {
        private String discountCardNumber;
        private int discount;
        private Double totalPrice;
        private Double totalDiscount;
        private Double totalWithDiscount;
        private List<ProductsPriceDiscountInfo> productsInfo;
        private Double userBalance;

        public CheckBuilder(String discountCardNumber) {
            this.discountCardNumber = discountCardNumber;
        }

        public CheckBuilder setDiscount(int discount) {
            this.discount = discount;
            return this;
        }

        public CheckBuilder setTotalPrice(Double totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

        public CheckBuilder setTotalDiscount(Double totalDiscount) {
            this.totalDiscount = totalDiscount;
            return this;
        }

        public CheckBuilder setTotalWithDiscount(Double totalWithDiscount) {
            this.totalWithDiscount = totalWithDiscount;
            return this;
        }

        public CheckBuilder setProductsInfo(List<ProductsPriceDiscountInfo> productsInfo) {
            this.productsInfo = productsInfo;
            return this;
        }

        public CheckBuilder setUserBalance(Double userBalance) {
            this.userBalance = userBalance;
            return this;
        }

        public Check build() {
            return new Check(this);
        }
    }

    @Override
    public String toString() {
        return "CheckService{" +
                "discountCardNumber='" + discountCardNumber + '\'' +
                ", discount=" + discount +
                ", totalPrice=" + totalPrice +
                ", totalDiscount=" + totalDiscount +
                ", totalWithDiscount=" + totalWithDiscount +
                ", productsInfo=" + productsInfo +
                ", userBalance=" + userBalance +
                '}';
    }
}
