package ru.clevertec.check.dto;

import ru.clevertec.check.utils.systemconsts.StringConst;

import java.util.ArrayList;
import java.util.List;

public class ResponseParsedArgsDto {

    private List<String> pairs = new ArrayList<>();
    private String numberDiscountCard = "none";
    private Double balance = 0.0;
    private String pathToFile = StringConst.NONE;
    private String saveToFile = StringConst.NONE;

    public ResponseParsedArgsDto() {
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

    public String getPathToFile() {
        return pathToFile;
    }

    public void setPathToFile(String pathToFile) {
        this.pathToFile = pathToFile;
    }

    public String getSaveToFile() {
        return saveToFile;
    }

    public void setSaveToFile(String saveToFile) {
        this.saveToFile = saveToFile;
    }

    @Override
    public String toString() {
        return "ResponseParsedArgsDto{" +
                "pairs=" + pairs +
                ", numberDiscountCard='" + numberDiscountCard + '\'' +
                ", balance=" + balance +
                '}';
    }
}
