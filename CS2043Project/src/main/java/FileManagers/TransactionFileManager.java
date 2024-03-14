package FileManagers;

import java.io.*;
import java.util.LinkedList;

/**
 * Represents a transaction with a name, type, amount, and optional description.
 */
class Transaction {
    private String name;
    private char type;
    private double amount;
    private String description;

    /**
     * Constructs a Transaction object with the given name, type, and amount.
     * @param name The name of the transaction.
     * @param type The type of the transaction ('I' for income, 'E' for expense).
     * @param amount The amount of the transaction.
     */
    public Transaction(String name, char type, double amount) {
        this(name, type, amount, null);
    }

    /**
     * Constructs a Transaction object with the given name, type, amount, and description.
     * @param name The name of the transaction.
     * @param type The type of the transaction ('I' for income, 'E' for expense).
     * @param amount The amount of the transaction.
     * @param description The description of the transaction.
     */
    public Transaction(String name, char type, double amount, String description) {
        this.name = name;
        this.type = type;
        this.amount = amount;
        this.description = description;
    }

    // Getters and setters

    /**
     * Retrieves the name of the transaction.
     * @return The name of the transaction.
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the type of the transaction.
     * @return The type of the transaction ('I' for income, 'E' for expense).
     */
    public char getType() {
        return type;
    }

    /**
     * Retrieves the amount of the transaction.
     * @return The amount of the transaction.
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Retrieves the description of the transaction.
     * @return The description of the transaction.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the transaction.
     * @param description The description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                '}';
    }
}

/**
 * Manages transactions by reading from and writing to a CSV file.
 */
public class TransactionFileManager {
    private static final String filePath = "C:\\Users\\afifs\\OneDrive\\Desktop\\Project\\CS2043Project\\data\\transactions.csv";

    private static LinkedList<Transaction> transactions;

    /**
     * Constructs a TransactionFileManager object and reads transactions from the CSV file.
     */
    public TransactionFileManager() {
        transactions = readTransactions();
    }

    /**
     * Reads transactions from the CSV file.
     * @return A LinkedList containing the read transactions.
     */
    private static LinkedList<Transaction> readTransactions() {
        LinkedList<Transaction> tempTransactions = new LinkedList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String transactionName = parts[0].trim();
                    char transactionType = parts[1].charAt(0);
                    double transactionAmount = Double.parseDouble(parts[2].trim());
                    String description = parts[3].trim();

                    tempTransactions.add(new Transaction(transactionName, transactionType, transactionAmount, description));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tempTransactions;
    }

    /**
     * Writes a transaction to the CSV file.
     * @param name The name of the transaction.
     * @param type The type of the transaction ('I' for income, 'E' for expense).
     * @param amount The amount of the transaction.
     * @param description The description of the transaction.
     */
    public static void writeTransaction(String name, char type, double amount, String description) {
        Transaction transaction = new Transaction(name, type, amount, description);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(name + "," + type + "," + amount + "," + description + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        transactions.add(transaction);
    }

    /**
     * Prints the list of transactions to the console.
     */
    public void printList() {
        System.out.println("List of Transactions:");
        for (Transaction transaction : transactions) {
            System.out.println("Name: " + transaction.getName());
            System.out.println("Type: " + transaction.getType());
            System.out.println("Amount: " + transaction.getAmount());
            if (transaction.getDescription() != null) {
                System.out.println("Description: " + transaction.getDescription());
            }
            System.out.println(); // Add a blank line between transactions
        }
    }
}
