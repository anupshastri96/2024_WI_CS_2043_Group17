package Objects;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Objects;

/**
 * Represents a budget category with a name, a set budget amount,
 * and a list of transactions associated with this category.
 */
public class Category implements Serializable {
    private int userid;
    private String name;
    private double budget;
    private LinkedList<Transaction> transactions;
    private LinkedList<Expense> expenses;


    /**
     * Constructs a new Category with a specified name and budget.
     * Initializes an empty list of transactions.
     *
     * @param name The name of the category.
     * @param budget The budget amount for the category.
     */
    public Category(int userid, String name, double budget){
        this.userid = userid;
        this.name = name;
        this.budget = budget;

        this.transactions = new LinkedList<>();
        this.expenses = new LinkedList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LinkedList<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(LinkedList<Transaction> transactions) {
        this.transactions = transactions;
    }

    public LinkedList<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(LinkedList<Expense> expenses) {
        this.expenses = expenses;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public boolean isOverBudget(double expenses){
        return expenses > budget;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(name, category.name);
    }


}
