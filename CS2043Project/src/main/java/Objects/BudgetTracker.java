/**
 * The BudgetTracker class represents a tool for managing budgets, expenses, and transactions.
 * It provides functionality to add and remove categories, expenses, and transactions,
 * as well as methods to retrieve and print lists of categories, expenses, and transactions.
 */
package Objects;

import java.util.LinkedList;

public class BudgetTracker {
    private LinkedList<Category> categoryLinkedList;
    private LinkedList<Expense> expenseLinkedList;
    private LinkedList<Transaction> transactionLinkedList;
    private LinkedList<Goal> goalLinkedList;

    /**
     * Constructs a new BudgetTracker with empty lists for categories, expenses, and transactions.
     */
    public BudgetTracker(){
        categoryLinkedList = new LinkedList<>();
        expenseLinkedList = new LinkedList<>();
        transactionLinkedList = new LinkedList<>();
        goalLinkedList = new LinkedList<>();
    }

    // Add and remove Categories

    /**
     * Adds a new category to the budget tracker.
     *
     * @param category The category to add.
     * @return true if the category was successfully added; false otherwise.
     */
    public boolean addCategory(Category category){
        return categoryLinkedList.add(category);
    }

    /**
     * Removes a category from the budget tracker.
     *
     * @param category The category to remove.
     * @return true if the category was successfully removed; false otherwise.
     */
    public boolean removeTransaction(Category category){
        return categoryLinkedList.remove(category);
    }

    // Add and remove transactions

    /**
     * Adds a new transaction to the budget tracker.
     *
     * @param transaction The transaction to add.
     * @return true if the transaction was successfully added; false otherwise.
     */
    public boolean addTransaction(Transaction transaction){
        transaction.getCategory().getTransactions().add(transaction);
        return transactionLinkedList.add(transaction);
    }

    /**
     * Removes a transaction from the budget tracker.
     *
     * @param transaction The transaction to remove.
     * @return true if the transaction was successfully removed; false otherwise.
     */
    public boolean removeTransaction(Transaction transaction){
        transaction.getCategory().getTransactions().remove(transaction);
        return transactionLinkedList.remove(transaction);
    }

    // Add and remove expenses

    /**
     * Adds a new expense to the budget tracker.
     *
     * @param expense The expense to add.
     * @return true if the expense was successfully added; false otherwise.
     */
    public boolean addExpense(Expense expense){
        expense.getCategory().getExpenses().add(expense);
        return expenseLinkedList.add(expense);
    }

    /**
     * Removes an expense from the budget tracker.
     *
     * @param expense The expense to remove.
     * @return true if the expense was successfully removed; false otherwise.
     */
    public boolean removeExpense(Expense expense){
        expense.getCategory().getExpenses().remove(expense);
        return expenseLinkedList.remove(expense);
    }

    // Add and remove goals
    public void addGoal(Goal goal){
        goalLinkedList.add(goal);
    }
    public void addGoal(String name, double amount, int duration){
        Goal toAdd = new Goal(name, amount, duration);
        goalLinkedList.add(toAdd);
    }
    public void removeGoal(Goal goal){
        goalLinkedList.remove(goal);
    }


    // Retrieve and change lists
    /**
     * Retrieves the list of categories.
     *
     * @return The list of categories.
     */
    public LinkedList<Category> getCategoryLinkedList() {
        return categoryLinkedList;
    }

    /**
     * Sets the list of categories.
     *
     * @param categoryLinkedList The new list of categories.
     */
    public void setCategoryLinkedList(LinkedList<Category> categoryLinkedList) {
        this.categoryLinkedList = categoryLinkedList;
    }

    /**
     * Retrieves the list of expenses.
     *
     * @return The list of expenses.
     */
    public LinkedList<Expense> getExpenseLinkedList() {
        return expenseLinkedList;
    }

    /**
     * Sets the list of expenses.
     *
     * @param expenseLinkedList The new list of expenses.
     */
    public void setExpenseLinkedList(LinkedList<Expense> expenseLinkedList) {
        this.expenseLinkedList = expenseLinkedList;
    }

    /**
     * Retrieves the list of transactions.
     *
     * @return The list of transactions.
     */
    public LinkedList<Transaction> getTransactionLinkedList() {
        return transactionLinkedList;
    }

    /**
     * Sets the list of transactions.
     *
     * @param transactionLinkedList The new list of transactions.
     */
    public void setTransactionLinkedList(LinkedList<Transaction> transactionLinkedList) {
        this.transactionLinkedList = transactionLinkedList;
    }

    /**
     * Prints a summary of all categories, including their names, budget amounts, and the number of transactions.
     */
    public void printCategoryList() {
        System.out.println("Categories Overview");
        System.out.println("-----------------------------------------------------------------");
        System.out.printf("%-20s %-20s %-20s\n", "Category Name", "Budget", "Transactions Count");
        System.out.println("-----------------------------------------------------------------");

        for (Category category : categoryLinkedList) {
            int transactionsCount = category.getTransactions().size();
            System.out.printf("%-20s %-20.2f %-20d\n", category.getName(), category.getBudget(), transactionsCount);
        }

        System.out.println("-----------------------------------------------------------------");
    }

    /**
     * Prints a list of all expenses, including their details such as date, ID, name, term, amount, and description.
     */
    public void printExpenseList() {
        System.out.printf("------------------------------------------------------------------------------------------------------------------------%n");
        System.out.printf("| %-10s | %-15s | %-8s | %-10s | %-8s | %-50s |%n", "Date", "Expense ID", "Name", "Term", "Amount", "Description");
        System.out.printf("------------------------------------------------------------------------------------------------------------------------%n");
        for (Expense expense : expenseLinkedList) {
            System.out.printf("| %-10s | %-15d | %-8s | %-10s | %-8.2f | %-50s |%n",
                    expense.getDate(),
                    expense.getTransactionId(),
                    expense.getName(),
                    expense.getTerm().name(),
                    expense.getAmount(),
                    expense.getDescription()
            );
        }
        System.out.printf("------------------------------------------------------------------------------------------------------------------------%n");
    }

    /**
     * Prints a list of all transactions, including their details such as date, ID, name, type, amount, and description.
     */
    public void printTransactionList() {
        System.out.printf("------------------------------------------------------------------------------------------------------------------%n");
        System.out.printf("| %-10s | %-15s | %-8s | %-4s | %-8s | %-50s |%n", "Date", "Transaction ID", "Name", "Type", "Amount", "Description");
        System.out.printf("------------------------------------------------------------------------------------------------------------------%n");
        for (Transaction transaction : transactionLinkedList) {
            System.out.printf("| %-10s | %-15d | %-8s | %-4c | %-8.2f | %-50s |%n",
                    transaction.getDate(),
                    transaction.getTransactionId(),
                    transaction.getName(),
                    transaction.getType(),
                    transaction.getAmount(),
                    transaction.getDescription()
            );
        }
        System.out.printf("------------------------------------------------------------------------------------------------------------------%n");
    }
}
