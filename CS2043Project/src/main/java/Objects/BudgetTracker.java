/**
 * The BudgetTracker class represents a tool for managing budgets, expenses, and transactions.
 * It provides functionality to add and remove categories, expenses, and transactions,
 * as well as methods to retrieve and print lists of categories, expenses, and transactions.
 */
package Objects;

import java.sql.Date;
import java.util.LinkedList;
import Database.*;

public class BudgetTracker {
    private LinkedList<Category> categoryLinkedList;

    private LinkedList<Transaction> transactionLinkedList;
    private LinkedList<Goal> goalLinkedList;

    /**
     * Constructs a new BudgetTracker with empty lists for categories, expenses, and transactions.
     */
    public BudgetTracker(int userId){
        categoryLinkedList = DB_Category.getCategoryList(userId);
        transactionLinkedList = DB_Transaction.getTransactionList(userId);
        goalLinkedList = new LinkedList<>();
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


//    /**
//     * Prints a summary of all categories, including their names, budget amounts, and the number of transactions.
//     */
//    public void printCategoryList() {
//        System.out.println("Categories Overview");
//        System.out.println("-----------------------------------------------------------------");
//        System.out.printf("%-20s %-20s %-20s\n", "Category Name", "Budget", "Transactions Count");
//        System.out.println("-----------------------------------------------------------------");
//
//        for (Category category : categoryLinkedList) {
//            int transactionsCount = category.getTransactions().size();
//            System.out.printf("%-20s %-20.2f %-20d\n", category.getName(), category.getBudget(), transactionsCount);
//        }
//
//        System.out.println("-----------------------------------------------------------------");
//    }
//
//    /**
//     * Prints a list of all expenses, including their details such as date, ID, name, term, amount, and description.
//     */
//    public void printExpenseList() {
//        System.out.printf("------------------------------------------------------------------------------------------------------------------------%n");
//        System.out.printf("| %-10s | %-15s | %-8s | %-10s | %-8s | %-50s |%n", "Date", "Expense ID", "Name", "Term", "Amount", "Description");
//        System.out.printf("------------------------------------------------------------------------------------------------------------------------%n");
//        for (Expense expense : expenseLinkedList) {
//            System.out.printf("| %-10s | %-15d | %-8s | %-10s | %-8.2f | %-50s |%n",
//                    expense.getDate(),
//                    expense.getTransactionId(),
//                    expense.getName(),
//                    expense.getTerm().name(),
//                    expense.getAmount(),
//                    expense.getDescription()
//            );
//        }
//        System.out.printf("------------------------------------------------------------------------------------------------------------------------%n");
//    }
//
//    /**
//     * Prints a list of all transactions, including their details such as date, ID, name, type, amount, and description.
//     */
//    public void printTransactionList() {
//        System.out.printf("------------------------------------------------------------------------------------------------------------------%n");
//        System.out.printf("| %-10s | %-15s | %-8s | %-4s | %-8s | %-50s |%n", "Date", "Transaction ID", "Name", "Type", "Amount", "Description");
//        System.out.printf("------------------------------------------------------------------------------------------------------------------%n");
//        for (Transaction transaction : transactionLinkedList) {
//            System.out.printf("| %-10s | %-15d | %-8s | %-4c | %-8.2f | %-50s |%n",
//                    transaction.getDate(),
//                    transaction.getTransactionId(),
//                    transaction.getName(),
//                    transaction.getType(),
//                    transaction.getAmount(),
//                    transaction.getDescription()
//            );
//        }
//        System.out.printf("------------------------------------------------------------------------------------------------------------------%n");
//    }

}
