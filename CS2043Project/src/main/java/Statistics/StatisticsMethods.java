package Statistics;

import Objects.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedList;
import Exceptions.DateFormatException;

public class StatisticsMethods {

    public StatisticsMethods(){}

    /**
     * Calculates amount over or under a given budget.
     * @param list the list to be searched.
     * @param category the category to search for.
     * @param startDate of time period to search.
     * @param endDate of time period to search.
     * @return Amount over or under budget (positive under, negative over)
     * @throws DateFormatException if start date is after end date.
     */
    public double budgetAmountRemaining(LinkedList<Transaction> list, Category category, Date startDate, Date endDate) throws DateFormatException {
        return category.getBudget() - totalExpenses(list, category, startDate, endDate);
    }

    /**
     * Calculates amount over or under a given budget.
     * @param totalSpent total amount spent for budget.
     * @param category the category holding the budget.
     * @return Amount over or under budget (positive under, negative over)
     */
    public double budgetAmountRemaining(double totalSpent, Category category) {
        return category.getBudget() - totalSpent;
    }

    /**
     * Sums all expenses over a given time period and category.
     * @param category the category to search for.
     * @param startDate of time period to search.
     * @param endDate of time period to search.
     * @return total amount spent in specified time range for a category.
     * @throws DateFormatException if start date is after end date.
     */
    public double totalExpenses(Category category, Date startDate, Date endDate) throws DateFormatException {
        if (startDate.compareTo(endDate) >= 0)
            throw new DateFormatException();

        LinkedList<Transaction> list = category.getTransactions();
        double totalExpenses = 0;

        for (Transaction transaction : list) {
            Date date = transaction.getDate();

            if (transaction instanceof Expense &&
                    startDate.compareTo(date) <= 0 &&
                    endDate.compareTo(date) >= 0){
                totalExpenses += transaction.getAmount();
            }
        }
        return totalExpenses;
    }

    /**
     * Sums all expenses over a given time period and category.
     * @param list the list to be searched.
     * @param category the category to search for.
     * @param startDate of time period to search.
     * @param endDate of time period to search.
     * @return total amount spent in specified time range and category.
     * @throws DateFormatException if start date is after end date.
     */
    public double totalExpenses(LinkedList<Transaction> list, Category category, Date startDate, Date endDate) throws DateFormatException {
        if (startDate.compareTo(endDate) >= 0)
            throw new DateFormatException();

        double totalExpenses = 0;
        for (Transaction transaction : list) {

            Date transactionDate = transaction.getDate();
            Category transactionCategory = transaction.getCategory();

            if (transaction instanceof Expense &&
                    startDate.compareTo(transactionDate) <= 0 &&
                    endDate.compareTo(transactionDate) >= 0 &&
                    transactionCategory.equals(category)) {

                totalExpenses += transaction.getAmount();
            }
        }
        return totalExpenses;
    }
}
