package MiniProject;
/**
 * Represents a transaction with a name, type, amount, and optional description.
 */
class Transaction{
    //private User user;
    private static int transID = 0;
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
    //public Transaction(String name, char type, double amount) {
    //    this(name, type, amount, null);
    //}

    /**
     * Constructs a Transaction object with the given name, type, amount, and description.
     * @param name The name of the transaction.
     * @param type The type of the transaction ('I' for income, 'E' for expense).
     * @param amount The amount of the transaction.
     * @param description The description of the transaction.
     */
    public Transaction(String name, char type, double amount, String description) {
        transID++;
        this.name = name;
        this.type = type;
        this.amount = amount;
        this.description = description;
    }

    // Getters and setters
    public static int getTransID(){
        return transID;
    }
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
    /*public String toString() {
        return "Transaction{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                '}';
    }*/

    public String toString(){
        return "Transaction: " + transID + "\t" + name + "\t" + amount + "\t" + type + "\t" + description;
    }
}