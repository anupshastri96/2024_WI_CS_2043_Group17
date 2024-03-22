package Statistics;

import Transactional.Category;
import Transactional.DateFormatException;
import Transactional.Expense;
import Transactional.Transaction;

import java.time.LocalDate;
import java.util.ArrayList;

public class StatisticsMethods {

    public StatisticsMethods(){}

    /**
     * Sums all expenses over a given time period and category.
     * @param list the list to be searched.
     * @param category the category to search for.
     * @param startDate of time period to search.
     * @param endDate of time period to search.
     * @return total amount spent in specified time range and category.
     * @throws DateFormatException if start date is after end date.
     */
    public double totalSpent(ArrayList<Transaction> list, Category category, LocalDate startDate, LocalDate endDate) throws DateFormatException {
        if (startDate.compareTo(endDate) >= 0)
            throw new DateFormatException();

        double totalExpenses = 0;
        for (Transaction transaction : list) {

            LocalDate transactionDate = transaction.getDateCreated();
            Category transactionCategory = transaction.getCategory();

            if (transaction instanceof Expense &&
                    startDate.compareTo(transactionDate) <= 0 &&
                    endDate.compareTo(transactionDate) >= 0 &&
                    transactionCategory.equals(category)) {

                totalExpenses += transaction.getTransactionAmount();
            }
        }
        return totalExpenses;
    }

    /**
     * Calculates amount over or under a given budget.
     * @param list the list to be searched.
     * @param category the category to search for.
     * @param startDate of time period to search.
     * @param endDate of time period to search.
     * @return Amount over or under budget (positive under, negative over)
     * @throws DateFormatException if start date is after end date.
     */
    public double amountOverBudget(ArrayList<Transaction> list, Category category, LocalDate startDate, LocalDate endDate) throws DateFormatException {
        return category.getBudgetAmount() + totalSpent(list, category, startDate, endDate);
    }

    /**
     * Calculates amount over or under a given budget.
     * @param totalSpent total amount spent for budget.
     * @param category the category holding the budget.
     * @return Amount over or under budget (positive under, negative over)
     */
    public double amountOverBudget(double totalSpent, Category category) {
        return category.getBudgetAmount() + totalSpent;
    }

}
