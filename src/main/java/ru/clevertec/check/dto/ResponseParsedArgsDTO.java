package ru.clevertec.check.dto;

import java.util.ArrayList;
import java.util.List;

public class ResponseParsedArgsDTO {

    private List<String> pairs = new ArrayList<>();
    private String numberDiscountCard = "none";
    private Double balance = 0.0;

    public ResponseParsedArgsDTO() {
    }

    public List<String> getPairs() {
        return pairs;
    }

    public void setPairs(List<String> pairs) {
        this.pairs = pairs;
    }

    public String getNumberDiscountCard() {
        return numberDiscountCard;
    }

    public void setNumberDiscountCard(String numberDiscountCard) {
        this.numberDiscountCard = numberDiscountCard;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "ResponseParsedArgsDTO{" +
                "pairs=" + pairs +
                ", numberDiscountCard='" + numberDiscountCard + '\'' +
                ", balance=" + balance +
                '}';
    }
}
