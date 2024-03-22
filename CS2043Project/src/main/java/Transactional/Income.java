package Transactional;

import java.time.LocalDate;

public class Income extends Transaction {

    public Income(int transactionID, double transactionAmount, LocalDate dateCreated, String transactionSource, Category category) {
        super(transactionID, transactionAmount, dateCreated, transactionSource, category);
    }

    @Override
    public String toString() {
        return null;
    }

}
