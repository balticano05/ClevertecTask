package ru.clevertec;

public class CheckRunner {
    public static void main(String[] args) {
        String ARGS = "3-1 2-5 5-1 discountCard=4444 balanceDebitCard=100.50";

        SystemCheckGenerator systemCheckGenerator = new SystemCheckGenerator();
        systemCheckGenerator.generate(ARGS);
    }
}