package Objects;

import Database.DB_Category;
import Database.DB_Transaction;
import Database.DB_User;
import Exceptions.UserNotFoundException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BudgetApplication {

    public static void main(String[] args) {
        String userInput = null;
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
                    4 | Expenses...
                    5 | Goals...
                    6 | Categories...
                    7 | Change Budget
                    8 | Import From CSV...""");


            userInput = scanner.next();
            switch (userInput) {
                case "0" -> programRunning = false;
                case "1" -> addTransaction(scanner, username);
                case "2" -> statementsMenu();
                case "3" -> transactionsMenu(scanner, username);
                case "4" -> expensesMenu(scanner);
                case "5" -> goalsMenu();
                case "6" -> categoriesMenu(scanner);
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

    private static void expensesMenu(Scanner scanner) {
        boolean menuOpen = true;
        while (menuOpen) {
            //Case 4 Expenses branch
            System.out.println("""
                    ==========================
                    0 | Previous Menu
                    1 | New Expense
                    2 | Edit Expense
                    3 | Delete Expense
                    """);
            String userInput = scanner.next();
            switch (userInput) {
                case "0" -> menuOpen = false;
                case "1" -> addExpense();
                case "2" -> updateExpense();
                case "3" -> deleteExpense();
                default -> {
                }
            }
        }
    }

    private static void addExpense() {
    }

    private static void updateExpense() {
    }

    private static void deleteExpense() {
    }

    private static void statementsMenu() {
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
                    result = false;
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
        boolean conditionCheck = false;
        while (!conditionCheck) {
            String username = null;
            boolean existingUserCondition = false;
            while (!existingUserCondition){
                try {
                    System.out.println("Register | Please enter username:");
                    username = scanner.next();
                    DB_User.getUserIDbyName(username);
                    System.out.println("Username is already in use");
                } catch (UserNotFoundException e) {
                    existingUserCondition = true;
                }
            }

            System.out.println("Register | Please enter password:");
            String password = scanner.next();
            System.out.println("Register | Please enter email:");
            String email = scanner.next();
            DB_User.addUser(username,password,email);
            conditionCheck = true;

        }
    }

    /**
     * Prompts user to sign in to service with credentials.
     * @param scanner scanner for user input.
     * @return the username of user.
     */
    private static String loginPrompt(Scanner scanner){
        boolean conditionCheck = false;
        String username = null;

        while (!conditionCheck) {
            try {
                System.out.println("Login | Please enter username:");
                username = scanner.next();
                System.out.println("Login | Please enter password:");
                String password = scanner.next();

                int userID = DB_User.getUserIDbyName(username);
                System.out.println(userID);
                User user = DB_User.getUser(userID);

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

    private static void addTransaction(Scanner scanner, String username) {
        int userID = 0;
        try {
            userID = DB_User.getUserIDbyName(username);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }

        boolean conditionCheck = false;
        while (!conditionCheck){

            System.out.println("New Transaction | Please enter Transaction name");
            String name = scanner.next();

            System.out.println("New Transaction | Please enter type; (W) for withdrawal, (D) for deposit: ");
            String type = scanner.next();
            if (!(type.equals("D") || type.equals("W"))) {
                System.out.println("New Transaction | Specified type is not valid:");
                return;
            }

            System.out.println("New Transaction | Please enter amount:");
            double amount = scanner.nextDouble();
            if (amount <= 0) {
                System.out.println("New Transaction | Amount cannot be negative:");
                return;
            }
            System.out.println("New Transaction | Please enter description or type (-) for null:");
            String description = scanner.next();
            if (description.equals("-"))
                description = null;

            System.out.println("New Transaction | Please enter category or type (-) for null:");
            String category = scanner.next();

            if (category.equals("-"))
                category = null;

            else if(DB_Category.getCategory(userID, category) == null){
                System.out.println("New Transaction | Category does not exist:");
            }

            DB_Transaction.addTransaction(userID, name, type.charAt(0), amount, description, category);
        }
    }

    private static void deleteTransaction() {
    }

    private static void updateTransaction() {
    }
}
