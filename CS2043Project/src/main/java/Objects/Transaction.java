package Objects;

import java.time.LocalDate;

/**
 * Represents a financial transaction, including details such as transaction ID,
 * date, name, type, amount, and description. It is designed to track and manage
 * individual financial transactions within a larger application.
 */
public class Transaction {

    private final int transactionId;
    private static int transactionIdCounter = 0; // A static counter for all transactions
    LocalDate date; // The date of the transaction
    private String name; // The name associated with the transaction
    private char type; // The type of transaction, e.g., 'I' for income, 'E' for expense
    private double amount; // The amount of the transaction
    private String description; // A description of the transaction
    private Category category;

    /**
     * Constructs a Transaction with detailed information including a name, type, amount, and description.
     * Automatically sets the transaction date to the current date and increments the transaction ID.
     *
     * @param name        The name of the transaction.
     * @param type        The type of the transaction (e.g., 'I' for income, 'E' for expense).
     * @param amount      The monetary amount of the transaction.
     * @param description A description of the transaction.
     */
    public Transaction(String name, char type, double amount, String description, Category category) {
        this.date = LocalDate.now();
        transactionId = ++transactionIdCounter;
        this.name = name;
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.category = category;
    }

    /**
     * Constructs a Transaction with basic information including a name, type, and amount.
     * Automatically sets the transaction date to the current date and increments the transaction ID.
     * The description is set to an empty string.
     *
     * @param name   The name of the transaction.
     * @param type   The type of the transaction.
     * @param amount The monetary amount of the transaction.
     */
    public Transaction(String name, char type, double amount, Category category) {
        this(name, type, amount, "",category);
    }

    /**
     * Returns the transaction ID. Note: This is static and increments with each new transaction across all instances.
     *
     * @return The transaction ID.
     */
    public int getTransactionId() {
        return transactionId;
    }

    /**
     * Returns the name of the transaction.
     *
     * @return The transaction name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the transaction.
     *
     * @param name The new name for the transaction.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the type of the transaction.
     *
     * @return The transaction type.
     */
    public char getType() {
        return type;
    }

    /**
     * Sets the type of the transaction.
     *
     * @param type The new type for the transaction.
     */
    public void setType(char type) {
        this.type = type;
    }

    /**
     * Returns the amount of the transaction.
     *
     * @return The transaction amount.
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Sets the amount of the transaction.
     *
     * @param amount The new amount for the transaction.
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * Returns the description of the transaction.
     *
     * @return The transaction description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the transaction.
     *
     * @param description The new description for the transaction.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the date of the transaction.
     *
     * @return The transaction date.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Sets the date of the transaction.
     *
     * @param date The new date for the transaction.
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Getter for category
     * @return The category
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Setter method for category
     * @param category Change the category
     */
    public void setCategory(Category category) {
        this.category = category;
    }
}

