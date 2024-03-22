package Transactional;

import java.time.LocalDate;

public abstract class Transaction {
    private int transactionID;
    private double transactionAmount;
    private LocalDate dateCreated;
    private String transactionSource;
    private Category category;

    public Transaction(int transactionID, double transactionAmount, LocalDate dateCreated, String transactionSource, Category category) {
        this.transactionID = transactionID;
        this.transactionAmount = transactionAmount;
        this.dateCreated = dateCreated;
        this.transactionSource = transactionSource;
        this.category = category;
    }

    public int getTransactionID() {
        return transactionID;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public String getTransactionSource() {
        return transactionSource;
    }

    public Category getCategory() {
        return category;
    }

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void setTransactionSource(String transactionSource) {
        this.transactionSource = transactionSource;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public abstract String toString();

}
