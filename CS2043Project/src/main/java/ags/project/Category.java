package ags.project;

import Lists.TransactionList;

import java.util.LinkedList;
import java.util.List;

public class Category {
    private String name;
    private double budget;
    private TransactionList transactions;

    public Category(String name, double budget){
        this.name = name;
        this.budget = budget;
        this.transactions = new TransactionList();
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TransactionList getTransactions() {
        return transactions;
    }
}

