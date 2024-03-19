package Lists;

import Objects.Expense;
import Objects.Transaction;
import java.util.LinkedList;
import Enum.Term;

/**
 * Represents a list of financial transactions, providing functionality to add, remove, and print transactions.
 * It encapsulates a LinkedList of Transaction objects, offering various methods to manipulate and view the transaction data.
 */
public class TransactionList {
    private final LinkedList<Transaction> transactionList;

    /**
     * Constructs a new, empty TransactionList.
     */
    public TransactionList() {
        transactionList = new LinkedList<>();
    }

    /**
     * Adds a transaction to the list.
     *
     * @param transaction The Transaction object to add.
     * @return true if the transaction was added successfully.
     */
    public boolean addTransaction(Transaction transaction) {
        return transactionList.add(transaction);
    }

    /**
     * Creates and adds a transaction to the list using the provided details.
     *
     * @param name The name of the transaction.
     * @param type The type of the transaction, represented as a character.
     * @param amount The amount of the transaction.
     */
    public void addTransaction(String name, char type, double amount) {
        Transaction transaction = new Transaction(name, type, amount);
        transactionList.add(transaction);
    }

    /**
     * Creates and adds a transaction to the list using the provided details, including a description.
     *
     * @param name The name of the transaction.
     * @param type The type of the transaction, represented as a character.
     * @param amount The amount of the transaction.
     * @param description A description of the transaction.
     * @return true if the transaction was added successfully.
     */
    public void addTransaction(String name, char type, double amount, String description) {
        Transaction transaction = new Transaction(name, type, amount, description);
        transactionList.add(transaction);
    }



    /**
     * Removes a specific transaction from the list.
     *
     * @param transaction The Transaction object to remove.
     */
    public void removeTransaction(Transaction transaction) {
        transactionList.removeIf(t -> t.equals(transaction));
    }

    /**
     * Removes a transaction from the list by its transaction ID.
     *
     * @param transactionId The ID of the transaction to remove.
     */
    public void removeTransaction(int transactionId) {
        transactionList.removeIf(t -> t.getTransactionId() == transactionId);
    }

    /**
     * Prints the list of transactions in a formatted table to the console.
     * The table includes columns for date, transaction ID, name, type, amount, and description.
     */
    public void printList() {
        System.out.printf("------------------------------------------------------------------------------------------------------------------%n");
        System.out.printf("| %-10s | %-15s | %-8s | %-4s | %-8s | %-50s |%n", "Date", "Transaction ID", "Name", "Type", "Amount", "Description");
        System.out.printf("------------------------------------------------------------------------------------------------------------------%n");
        for (Transaction transaction : transactionList) {
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
