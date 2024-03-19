package Lists;

import java.util.LinkedList;
import Enum.Term;
import Objects.Category;
import Objects.Expense;
import Objects.Transaction;

/**
 * The {@code ExpenseList} class manages a list of {@code Expense} objects.
 * It allows for the creation, modification, and retrieval of expenses within a linked list.
 */
public class ExpenseList {
    private LinkedList<Expense> expenseList;

    /**
     * Constructs an empty list of expenses.
     */
    public ExpenseList() {
        expenseList = new LinkedList<>();
    }

    /**
     * Returns the current list of expenses.
     *
     * @return A {@link LinkedList} of {@code Expense} objects representing the current list of expenses.
     */
    public LinkedList<Expense> getExpenseList() {
        return expenseList;
    }

    /**
     * Sets the list of expenses to a new list.
     *
     * @param expenseList A {@link LinkedList} of {@code Expense} objects to replace the current list.
     */
    public void setExpenseList(LinkedList<Expense> expenseList) {
        this.expenseList = expenseList;
    }

    /**
     * Adds a new expense to the list with a description.
     *
     * @param name        The name of the expense.
     * @param amount      The monetary amount of the expense.
     * @param description A description of the expense.
     * @param term        The term (short-term or long-term) of the expense.
     */
    public void addExpense(String name, double amount, String description, Term term, Category category) {
        Expense expense = new Expense(name, amount, description, term, category);
        expenseList.add(expense);
    }

    /**
     * Adds a new expense to the list without a description.
     *
     * @param name   The name of the expense.
     * @param amount The monetary amount of the expense.
     * @param term   The term (short-term or long-term) of the expense.
     */
    public void addExpense(String name, double amount, Term term, Category category) {
        Expense expense = new Expense(name, amount, term, category);
        expenseList.add(expense);
    }

    /**
     * Prints the list of expenses in a formatted table. This table includes columns for the date, expense ID,
     * name, term, amount, and description of each expense. This method assumes that the {@code Expense} class
     * has methods {@code getDate()}, {@code getTransactionId()}, in addition to the ones defined in the provided
     * {@code Expense} class code.
     */
    public void printList() {
        System.out.printf("------------------------------------------------------------------------------------------------------------------------%n");
        System.out.printf("| %-10s | %-15s | %-8s | %-10s | %-8s | %-50s |%n", "Date", "Expense ID", "Name", "Term", "Amount", "Description");
        System.out.printf("------------------------------------------------------------------------------------------------------------------------%n");
        for (Expense expense : expenseList) {
            System.out.printf("| %-10s | %-15d | %-8s | %-10s | %-8.2f | %-50s |%n",
                    expense.getDate(), // Assumes existence of getDate method
                    expense.getTransactionId(), // Assumes existence of getTransactionId method
                    expense.getName(),
                    expense.getTerm().name(),
                    expense.getAmount(),
                    expense.getDescription()
            );
        }
        System.out.printf("------------------------------------------------------------------------------------------------------------------------%n");
    }
}
