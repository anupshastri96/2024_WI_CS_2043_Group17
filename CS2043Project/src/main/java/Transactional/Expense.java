package Transactional;

import java.time.LocalDate;

public class Expense extends Transaction {

    public Expense(int transactionID, double transactionAmount, LocalDate dateCreated, String transactionSource, Category category) {
        super(transactionID, transactionAmount, dateCreated, transactionSource, category);
    }

    /**
     * When calling this method (for summation of transactions) the result will
     * be negated since it counts as money taken out of a users account.
     * @return the negated amount of an expense.
     */
    @Override
    public double getTransactionAmount() {
        return -super.getTransactionAmount();
    }

    @Override
    public String toString() {
        return null;
    }
}
