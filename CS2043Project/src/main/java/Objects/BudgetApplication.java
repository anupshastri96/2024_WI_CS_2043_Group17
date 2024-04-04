package Objects;

import Database.*;
import Exceptions.UserNotFoundException;
import Runnables.ChartTest;
import javafx.application.Application;

import java.sql.Connection;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BudgetApplication {
    private static User user;
    public static void main(String[] args) {
        String userInput;
        Scanner scanner = new Scanner(System.in);
        //Login Prompt
        if(!isExistingUser(scanner))
            registerPrompt(scanner);
        String username = loginPrompt(scanner);

        boolean programRunning = true;

        while(programRunning) {
            System.out.println("""
                    ==========================
                    0 | Quit
                    1 | Add Transaction
                    2 | Statements...
                    3 | Transactions...
                    4 | Goals...
                    5 | Categories...
                    6 | View Lists
                    7 | Change Budget
                    8 | Import From CSV...""");


            userInput = scanner.next();
            switch (userInput) {
                case "0" -> programRunning = false;
                case "1" -> addTransaction(scanner, username);
                case "2" -> statementsMenu(scanner, args);
                case "3" -> transactionsMenu(scanner, username);
                case "4" -> goalsMenu();
                case "5" -> categoriesMenu(scanner);
                case "6" -> viewListMenu(scanner);
                case "7" -> updateBudget();
                case "8" -> CSVMenu();
                default -> {
                }

            }
        }
    }

    private static void CSVMenu() {
    }

    private static void updateBudget() {
    }

    private static void categoriesMenu(Scanner scanner) {
        boolean menuOpen = true;
        while (menuOpen) {
            //Case 6 Category branch
            System.out.println("""
                    ==========================
                    0 | Previous Menu
                    1 | New Category
                    2 | Edit Category
                    3 | Delete Category
                    """);
            String userInput = scanner.next();
            switch (userInput) {
                case "0" -> menuOpen = false;
                case "1" -> addCategory();
                case "2" -> updateCategory();
                case "3" -> deleteCategory();
                default -> {
                }
            }
        }
    }

    private static void deleteCategory() {
    }

    private static void updateCategory() {
    }

    private static void addCategory() {
    }

    private static void goalsMenu() {
    }


    private static void statementsMenu(Scanner scanner, String[] args) {
        boolean menuOpen = true;
        while (menuOpen) {
            //Case 6 Category branch
            System.out.println("""
                    ==========================
                    0 | Previous Menu
                    1 | Create YTD budget chart
                    2 | -
                    3 | -
                    """);
            String userInput = scanner.next();
            switch (userInput) {
                case "0" -> menuOpen = false;
                case "1" -> ChartTest.launch(ChartTest.class, args);
                case "2" -> System.out.println("");
                case "3" -> deleteCategory();
                default -> {
                }
            }
        }
    }

    /**
     * Prompts user for input if they are a new or returning user.
     * @param scanner scanner for user input.
     * @return if they are an existing user.
     */
    private static boolean isExistingUser(Scanner scanner) {
        boolean conditionCheck = true;
        boolean result = false;

        //New or existing user prompt
        System.out.println("Welcome | to Personal Finance Manager");
        while (conditionCheck) {
            try {
                System.out.println("Are you a new (n) or returning user (r), enter n or r:");
                String input = scanner.next();
                if (input.equals("n")){
                    conditionCheck = false;
                }
                else if (input.equals("r")){
                    conditionCheck = false;
                    result = true;
                }
                else throw new InputMismatchException(input);
            } catch (InputMismatchException e) {
                System.out.println("<"+e.getMessage()+"> is not a valid input:");
            }
        }
        return result;
    }

    /**
     * Prompts user to create a new account.
     * @param scanner scanner for user input.
     */
    private static void registerPrompt(Scanner scanner){
        Connection dbConnection = DB_Access.Connect();

        boolean conditionCheck = false;
        while (!conditionCheck) {
            String username = null;
            boolean existingUserCondition = false;
            while (!existingUserCondition){
                try {
                    System.out.println("Register | Please enter username:");
                    username = scanner.next();
                    DB_User.getUserIDbyName(dbConnection,username);
                    System.out.println("Username is already in use");
                } catch (UserNotFoundException e) {
                    existingUserCondition = true;
                }
            }

            System.out.println("Register | Please enter password:");
            String password = scanner.next();
            System.out.println("Register | Please enter email:");
            String email = scanner.next();
            DB_User.addUser(dbConnection,username,password,email,0);
            conditionCheck = true;

        }
        DB_Access.Closing(dbConnection);
    }

    /**
     * Prompts user to sign in to service with credentials.
     * @param scanner scanner for user input.
     * @return the username of user.
     */
    private static String loginPrompt(Scanner scanner){
        Connection dbConnection = DB_Access.Connect();
        boolean conditionCheck = false;
        String username = null;

        while (!conditionCheck) {
            try {
                System.out.println("Login | Please enter username:");
                username = scanner.next();
                System.out.println("Login | Please enter password:");
                String password = scanner.next();

                System.out.println("Login | Verifying, please wait...");
                int userID = DB_User.getUserIDbyName(dbConnection,username);
                System.out.println(userID);
                user = DB_User.getUser(dbConnection,userID);

                if (user.password.equals(password)) {
                    conditionCheck = true;
                }
                else throw new InputMismatchException();
            } catch (InputMismatchException e) {
                System.out.println("Username or password is incorrect:");
            } catch (UserNotFoundException e) {
                System.out.println("User was not found");
            }
        }
        System.out.println("Login Successful | Welcome "+ username);
        System.out.println(user.getUserId());
        DB_Access.Closing(dbConnection);
        return username;
    }

    private static void transactionsMenu(Scanner scanner, String username){
        //Case 3 Transaction branch
        boolean menuOpen = true;
        while (menuOpen) {
            System.out.println("""
                    ==========================
                    0 | Previous Menu
                    1 | New Transaction
                    2 | Edit Transaction
                    3 | Delete Transaction
                    """);
            String userInput = scanner.next();
            switch (userInput) {
                case "0" -> menuOpen = false;
                case "1" -> addTransaction(scanner, username);
                case "2" -> updateTransaction();
                case "3" -> deleteTransaction();
                default -> {
                }
            }
        }
    }
    private static void viewListMenu(Scanner scanner){
        Connection dbConnection = DB_Access.Connect();
        //Case 3 Transaction branch
        boolean menuOpen = true;
        while (menuOpen) {
            System.out.println("""
                    ==========================
                    0 | Previous Menu
                    1 | View Transactions
                    2 | View Goals
                    3 | View Categories
                    """);
            String userInput = scanner.next();
            switch (userInput) {
                case "0" -> menuOpen = false;
                case "1" -> ListPrinters.printTransactionList(DB_Transaction.getTransactionList(dbConnection,user.getUserId()));
                case "2" -> ListPrinters.printGoalsTable(DB_Goal.getGoalList(dbConnection,user.getUserId()));
                case "3" -> ListPrinters.printCategoryList(DB_Category.getCategoryList(dbConnection,user.getUserId()));
                default -> {
                }
            }
        }

    }

    private static void addTransaction(Scanner scanner, String username) {
        Connection dbConnection = DB_Access.Connect();
        int userID;
        try {
            userID = DB_User.getUserIDbyName(dbConnection,username); // Get the user ID based on username
        } catch (UserNotFoundException e) {
            e.printStackTrace(); // Print the error if user is not found
            return;// Exit the method if the user is not found
        }

        while (true){
            System.out.println("New Transaction | Please enter Transaction name");
            String name = scanner.next();

            System.out.println("New Transaction | Please enter type; (W) for withdrawal, (D) for deposit: ");
            String type = scanner.next();
            if (!(type.equals("D") || type.equals("W"))) {
                System.out.println("New Transaction | Specified type is not valid:");
                continue; // Skip the rest of the loop iteration and start over
            }

            System.out.println("New Transaction | Please enter amount:");
            double amount = scanner.nextDouble();
            if (amount <= 0) {
                System.out.println("New Transaction | Amount cannot be negative:");
                continue; // Ask for the transaction details again
            }

            scanner.nextLine();

            System.out.println("New Transaction | Please enter description or type (-) for null:");
            String description = scanner.next();
            if (description.equals("-")) description = null;

            System.out.println("New Transaction | Please enter category or type (-) for null:");
            String category = scanner.nextLine();

            if (category.equals("-"))
                category = null;

            else if(DB_Category.getCategory(dbConnection,userID, category) == null){
                System.out.println("New Transaction | Category does not exist:");
                continue; // Ask for the transaction details again
            }

            DB_Transaction.addTransaction(dbConnection,userID, name, type.charAt(0), amount, description, category);
            System.out.println("Transaction added successfully.");
            break; // Break out of the loop since transaction is successfully added
        }
        DB_Access.Closing(dbConnection);
    }

    private static void deleteTransaction() {
        Connection dbConnect = DB_Access.Connect();
        ListPrinters.printTransactionList(DB_Transaction.getTransactionList(dbConnect, user.getUserId()));
        DB_Access.Closing(dbConnect);
    }

    private static void updateTransaction() {
    }
}
