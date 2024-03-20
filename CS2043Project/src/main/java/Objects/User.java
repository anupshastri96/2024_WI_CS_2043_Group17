package Objects;
import Lists.CategoryList;
import Lists.ExpenseList;
import Lists.TransactionList;

/**
 * Represents a user of the system, encapsulating user details such as username, password, and email,
 * along with their transactions and categories for managing personal finance.
 */
public class User {
    private int userId; // Unique identifier for the user
    private String username; // Username of the user
    protected String password; // Password for the user account
    private String email; // Email address of the user
    private TransactionList transactionList; // List of transactions associated with the user
    private CategoryList categoryList; // List of categories for budgeting associated with the user
    private ExpenseList expenseList;


    /**
     * Constructs a new User with specified username, password, and email. Initializes empty lists for transactions and categories.
     *
     * @param username The username for the user.
     * @param password The password for the user.
     * @param email The email address of the user.
     */
    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        transactionList = new TransactionList();
        categoryList = new CategoryList();
        expenseList = new ExpenseList();
    }

    /**
     * Gets the user's unique ID.
     *
     * @return The unique identifier for the user.
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the user's unique ID.
     *
     * @param userId The new unique identifier for the user.
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Gets the username of the user.
     *
     * @return The username of the user.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the user.
     *
     * @param username The new username for the user.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the email of the user.
     *
     * @return The email address of the user.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the user.
     *
     * @param email The new email address for the user.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the transaction list of the user.
     *
     * @return The list of transactions associated with the user.
     */
    public TransactionList getTransactionList() {
        return transactionList;
    }

    /**
     * Sets the transaction list for the user.
     *
     * @param transactionList The new list of transactions for the user.
     */
    public void setTransactionList(TransactionList transactionList) {
        this.transactionList = transactionList;
    }

    /**
     * Gets the category list of the user.
     *
     * @return The list of budgeting categories associated with the user.
     */
    public CategoryList getCategoryList() {
        return categoryList;
    }

    /**
     * Sets the category list for the user.
     *
     * @param categoryList The new list of budgeting categories for the user.
     */
    public void setCategoryList(CategoryList categoryList) {
        this.categoryList = categoryList;
    }

    /**
     * Attempts to log the user in with a username and password.
     *
     * @param username The username for login attempt.
     * @param password The password for login attempt.
     * @return true if login is successful, false otherwise.
     */
    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    /**
     * Attempts to change the user's password.
     *
     * @param oldPassword The current password for the user.
     * @param newPassword The new password to be set for the user.
     * @return true if the password was successfully changed, false otherwise.
     */
    public boolean changePassword(String oldPassword, String newPassword) {
        if (this.password.equals(oldPassword)) {
            this.password = newPassword;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Getter for the expense list.
     * @return The expense list.
     */
    public ExpenseList getExpenseList() {
        return expenseList;
    }

    /**
     * Setter for the expense list
     * @param expenseList The new expense list.
     */
    public void setExpenseList(ExpenseList expenseList) {
        this.expenseList = expenseList;
    }
}
