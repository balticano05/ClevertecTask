package ru.clevertec.check.entity.debit;

public class DebitCard {

    private double balance;

    public DebitCard() {
    }

    public DebitCard(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "DebitCard{" +
                "balance=" + balance +
                '}';
    }

}
