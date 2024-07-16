package ru.clevertec.check.entity.discount;

public class DiscountCard {

    private int id;
    private String cardNumber;
    private int discount;

    public DiscountCard() {
    }

    public DiscountCard(int id, String cardNumber, int discount) {
        this.id = id;
        this.cardNumber = cardNumber;
        this.discount = discount;
    }

    public int getId() {
        return id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public int getDiscount() {
        return discount;
    }

    private DiscountCard(DiscountCardBuilder builder) {
        this.id = builder.id;
        this.cardNumber = builder.cardNumber;
        this.discount = builder.discount;
    }


    public static class DiscountCardBuilder {
        private int id = -1;
        private String cardNumber = "none";
        private int discount = 0;

        public DiscountCardBuilder(int id) {
            this.id = id;
        }

        public DiscountCardBuilder setCardNumber(String cardNumber) {
            this.cardNumber = cardNumber;
            return this;
        }

        public DiscountCardBuilder setDiscount(int discount) {
            this.discount = discount;
            return this;
        }

        public DiscountCard build() {
            return new DiscountCard(this);
        }
    }

    @Override
    public String toString() {
        return "DiscountCard{" +
                "id=" + id +
                ", cardNumber='" + cardNumber + '\'' +
                ", discount=" + discount +
                '}';
    }

}
