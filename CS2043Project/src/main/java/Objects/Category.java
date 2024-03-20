package Objects;

import Lists.TransactionList;
import Lists.*;

/**
 * Represents a budget category with a name, a set budget amount,
 * and a list of transactions associated with this category.
 */
public class Category {
    private String name;
    private double budget;
    private TransactionList transactions;
    private ExpenseList expenses;


    /**
     * Constructs a new Category with a specified name and budget.
     * Initializes an empty list of transactions.
     *
     * @param name The name of the category.
     * @param budget The budget amount for the category.
     */
    public Category(String name, double budget){
        this.name = name;
        this.budget = budget;
        this.transactions = new TransactionList();
        this.expenses = new ExpenseList();
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public void setTransactions(TransactionList transactions) {
        this.transactions = transactions;
    }

    /**
     * Returns the name of the category.
     *
     * @return The name of the category.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the category.
     *
     * @param name The new name of the category.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the list of transactions for the category.
     *
     * @return The list of transactions.
     */
    public TransactionList getTransactions() {
        return transactions;
    }

    /**
     * Getter for the expense list.
     * @return The expense list.
     */
    public ExpenseList getExpenses() {
        return expenses;
    }

    /**
     *
     * @param expenses A new expense list.
     */
    public void setExpenses(ExpenseList expenses) {
        this.expenses = expenses;
    }
}
