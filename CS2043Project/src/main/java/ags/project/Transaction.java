package ags.project;

import java.time.LocalDate;

public class Transaction {
    private static int transactionId = 0;
    LocalDate date;
    private String name;
    private char type;
    private double amount;
    private String description;

    public Transaction(String name, char type, double amount, String description){
        date = date.now();
        transactionId++;
        this.name = name;
        this.type = type;
        this.amount = amount;
        this.description = description;
    }
    public Transaction(String name, char type, double amount){
        new Transaction(name, type, amount, "");
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
