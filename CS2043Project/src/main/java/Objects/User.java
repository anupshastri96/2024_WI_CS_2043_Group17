package Objects;

import Database.DB_Access;
import Database.DB_Transaction;
import Database.DB_User;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Represents a user of the system, encapsulating user details such as username, password, and email,
 * along with their transactions and categories for managing personal finance.
 */
public class User {
    private int userId; // Unique identifier for the user
    private String username; // Username of the user
    protected String password; // Password for the user account
    private String email; // Email address of the user
    private double monthlyIncome;


    /**
     * Constructs a new User with specified username, password, and email. Initializes empty lists for transactions and categories.
     *
     * @param username The username for the user.
     * @param password The password for the user.
     * @param email The email address of the user.
     */
    public User(int userId,  String username, String password, String email, double monthlyIncome) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.monthlyIncome = monthlyIncome;
    }


    public double getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(double monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
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
     * Attempts to log the user in with a username and password.
     * @param dbConnection the connection to the database.
     * @param username The username for login attempt.
     * @param password The password for login attempt.
     * @return true if login is successful, false otherwise.
     */
    public static User login(Connection dbConnection, String username, String password) {
        return DB_User.login(dbConnection, username, password);
    }

    public static User login(String username, String password) {
        Connection dbConnection = DB_Access.Connect();
        User user = DB_User.login(dbConnection, username, password);
        DB_Access.Closing(dbConnection);
        return user;
    }

    private String getPassword() {
        return password;
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
}
