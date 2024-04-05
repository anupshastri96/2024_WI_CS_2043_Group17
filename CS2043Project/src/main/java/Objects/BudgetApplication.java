package Objects;

import Database.DB_Category;
import Database.DB_Goal;
import Database.DB_Transaction;
import Database.DB_User;
import Exceptions.UserNotFoundException;
import Verification.Verify;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BudgetApplication {
//    private static User user;
//    public static void main(String[] args) {
//        String userInput;
//        Scanner scanner = new Scanner(System.in);
//        //Login Prompt
//        if(!isExistingUser(scanner))
//            registerPrompt(scanner);
//        String username = loginPrompt(scanner);
//
//        boolean programRunning = true;
//
//        while(programRunning) {
//            System.out.println("""
//                    ==========================
//                    0 | Quit
//                    1 | Add Transaction
//                    2 | Statements...
//                    3 | Transactions...
//                    4 | Goals...
//                    5 | Categories...
//                    6 | View Lists
//                    7 | Change Budget
//                    8 | Import From CSV...""");
//
//
//            userInput = scanner.next();
//            switch (userInput) {
//                case "0" -> programRunning = false;
//                case "1" -> addTransaction(scanner, username);
//                case "2" -> statementsMenu();
//                case "3" -> transactionsMenu(scanner, username);
//                case "4" -> goalsMenu();
//                case "5" -> categoriesMenu(scanner);
//                case "6" -> viewListMenu(scanner);
//                case "7" -> updateBudget();
//                case "8" -> CSVMenu();
//                default -> {
//                }
//
//            }
//        }
//    }
//
//    private static void CSVMenu() {
//    }
//
//    private static void updateBudget() {
//    }
//
//    private static void categoriesMenu(Scanner scanner) {
//        boolean menuOpen = true;
//        while (menuOpen) {
//            //Case 6 Category branch
//            System.out.println("""
//                    ==========================
//                    0 | Previous Menu
//                    1 | New Category
//                    2 | Edit Category
//                    3 | Delete Category
//                    """);
//            String userInput = scanner.next();
//            switch (userInput) {
//                case "0" -> menuOpen = false;
//                case "1" -> addCategory();
//                case "2" -> updateCategory();
//                case "3" -> deleteCategory();
//                default -> {
//                }
//            }
//        }
//    }
//
//    private static void deleteCategory() {
//    }
//
//    private static void updateCategory() {
//    }
//
//    private static void addCategory() {
//    }
//
//    private static void goalsMenu() {
//    }
//
//
//    private static void statementsMenu() {
//    }
//
//    /**
//     * Prompts user for input if they are a new or returning user.
//     * @param scanner scanner for user input.
//     * @return if they are an existing user.
//     */
//    private static boolean isExistingUser(Scanner scanner) {
//        boolean conditionCheck = true;
//        boolean result = false;
//
//        //New or existing user prompt
//        System.out.println("Welcome | to Personal Finance Manager");
//        while (conditionCheck) {
//            try {
//                System.out.println("Are you a new (n) or returning user (r), enter n or r:");
//                String input = scanner.next();
//                if (input.equals("n")){
//                    conditionCheck = false;
//                }
//                else if (input.equals("r")){
//                    conditionCheck = false;
//                    result = true;
//                }
//                else throw new InputMismatchException(input);
//            } catch (InputMismatchException e) {
//                System.out.println("<"+e.getMessage()+"> is not a valid input:");
//            }
//        }
//        return result;
//    }
//
//    /**
//     * Prompts user to create a new account.
//     * @param scanner scanner for user input.
//     */
//    private static void registerPrompt(Scanner scanner){
//        boolean conditionCheck = false;
//        while (!conditionCheck) {
//            String username = null;
//            boolean existingUserCondition = false;
//            while (!existingUserCondition){
//                try {
//                    System.out.println("Register | Please enter username:");
//                    username = scanner.next();
//                    DB_User.getUserIDbyName(username);
//                    System.out.println("Username is already in use");
//                } catch (UserNotFoundException e) {
//                    existingUserCondition = true;
//                }
//            }
//
//            System.out.println("Register | Please enter password:");
//            String password = scanner.next();
//            System.out.println("Register | Please enter email:");
//            String email = scanner.next();
//            DB_User.addUser(username,password,email,0);
//            conditionCheck = true;
//
//        }
//    }
//
//    /**
//     * Prompts user to sign in to service with credentials.
//     * @param scanner scanner for user input.
//     * @return the username of user.
//     */
//    private static String loginPrompt(Scanner scanner){
//        boolean conditionCheck = false;
//        String username = null;
//
//        while (!conditionCheck) {
//            try {
//                System.out.println("Login | Please enter username:");
//                username = scanner.next();
//                System.out.println("Login | Please enter password:");
//                String password = scanner.next();
//
//                System.out.println("Login | Verifying, please wait...");
//                int userID = DB_User.getUserIDbyName(username);
//                System.out.println(userID);
//                user = DB_User.getUser(userID);
//
//                if (user.password.equals(password)) {
//                    conditionCheck = true;
//                }
//                else throw new InputMismatchException();
//            } catch (InputMismatchException e) {
//                System.out.println("Username or password is incorrect:");
//            } catch (UserNotFoundException e) {
//                System.out.println("User was not found");
//            }
//        }
//        System.out.println("Login Successful | Welcome "+ username);
//        System.out.println(user.getUserId());
//        return username;
//    }
//
//    private static void transactionsMenu(Scanner scanner, String username){
//        //Case 3 Transaction branch
//        boolean menuOpen = true;
//        while (menuOpen) {
//            System.out.println("""
//                    ==========================
//                    0 | Previous Menu
//                    1 | New Transaction
//                    2 | Edit Transaction
//                    3 | Delete Transaction
//                    """);
//            String userInput = scanner.next();
//            switch (userInput) {
//                case "0" -> menuOpen = false;
//                case "1" -> addTransaction(scanner, username);
//                case "2" -> updateTransaction(scanner);
//                case "3" -> deleteTransaction(scanner);
//                default -> {
//                }
//            }
//        }
//    }
//
//
//    private static void addTransaction(Scanner scanner, String username) {
//        int userID;
//        try {
//            userID = DB_User.getUserIDbyName(username); // Get the user ID based on username
//        } catch (UserNotFoundException e) {
//            e.printStackTrace(); // Print the error if user is not found
//            return;// Exit the method if the user is not found
//        }
//
//        while (true){
//            System.out.println("New Transaction | Please enter Transaction name");
//            String name = scanner.next();
//
//            System.out.println("New Transaction | Please enter type; (W) for withdrawal, (D) for deposit: ");
//            String type = scanner.next();
//            if (!(type.equals("D") || type.equals("W"))) {
//                System.out.println("New Transaction | Specified type is not valid:");
//                continue; // Skip the rest of the loop iteration and start over
//            }
//
//            System.out.println("New Transaction | Please enter amount:");
//            double amount = scanner.nextDouble();
//            if (amount <= 0) {
//                System.out.println("New Transaction | Amount cannot be negative:");
//                continue; // Ask for the transaction details again
//            }
//
//            scanner.nextLine();
//
//            System.out.println("New Transaction | Please enter description or type (-) for null:");
//            String description = scanner.next();
//            if (description.equals("-")) description = null;
//
//            System.out.println("New Transaction | Please enter category or type (-) for null:");
//            String category = scanner.nextLine();
//
//            if (category.equals("-"))
//                category = null;
//
//            else if(DB_Category.getCategory(userID, category) == null){
//                System.out.println("New Transaction | Category does not exist:");
//                continue; // Ask for the transaction details again
//            }
//
//            DB_Transaction.addTransaction(userID, name, type.charAt(0), amount, description, category);
//            System.out.println("Transaction added successfully.");
//            break; // Break out of the loop since transaction is successfully added
//        }
//    }
//
//    private static void deleteTransaction(Scanner scanner) {
//        ListPrinters.printTransactionList(DB_Transaction.getTransactionList(user.getUserId()));
//        System.out.println("Delete Transaction | Please enter Transaction ID:");
//        int transactionId = scanner.nextInt();
//        boolean found = Verify.doesTransactionExist(transactionId, DB_Transaction.getTransactionList(user.getUserId()));
//        if(found)
//            DB_Transaction.deleteTransaction(transactionId);
//        else{
//            System.out.println("Delete Transaction | Error, no transaction with that id found!");
//        }
//    }
//
//    private static void updateTransaction(Scanner scanner) {
//        ListPrinters.printTransactionList(DB_Transaction.getTransactionList(user.getUserId()));
//        System.out.println("Update Transaction | Please enter Transaction ID:");
//        int transactionId = scanner.nextInt();
//        scanner.nextLine(); // Consume the newline left-over
//        System.out.println("Please wait...");
//        boolean found = Verify.doesTransactionExist(transactionId, DB_Transaction.getTransactionList(user.getUserId()));
//        if (found) {
//            Transaction transaction = DB_Transaction.getTransactionById(transactionId);
//
//            System.out.println("Update Transaction | Enter new name, or type (-) for no change:");
//            String name = scanner.nextLine();
//            if (name.equals("-")) {
//                name = transaction.getName();
//            }
//
//            System.out.println("Update Transaction | Please input updated date (YYYY-MM-DD):");
//            String date = scanner.nextLine();
//            if (!Verify.isValidDate(date) && !date.equals("-")) {
//                System.out.println("Date not correct!");
//                return; // Stop execution if the date is invalid
//            }
//            if (date.equals("-")) {
//                date = transaction.getDate();
//            }
//
//            System.out.println("Update Transaction | Enter new Type or type (-) for no change:");
//            char type = scanner.nextLine().charAt(0);
//            if (type == '-') {
//                type = transaction.getType();
//            }
//
//            System.out.println("Update Transaction | Enter new amount or type (-1) for no change:");
//            double amount = scanner.nextDouble();
//            scanner.nextLine(); // Consume the newline
//            if (amount == -1) {
//                amount = transaction.getAmount(); // Use getAmount here
//            }
//
//            System.out.println("Update Transaction | Enter new description or type (-) for no change:");
//            String description = scanner.nextLine();
//            if (description.equals("-")) {
//                description = transaction.getDescription();
//            }
//
//            System.out.println("Update Transaction | Enter new category or type (-) for no change:");
//            String category = scanner.nextLine();
//            if (!category.equals("-") && !Verify.doesCategoryExist(user.getUserId(), category)) {
//                System.out.println("Category does not exist. No change made.");
//                category = transaction.getCategory().getName();
//            } else if (category.equals("-")) {
//                category = transaction.getCategory().getName();
//            }
//
//            DB_Transaction.updateTransaction(transactionId, date, name, type, amount, description, category);
//        } else {
//            System.out.println("Update Transaction | Error, no transaction with that id found!");
//        }
//    }
//
//    private static void viewListMenu(Scanner scanner){
//        boolean menuOpen = true;
//        while (menuOpen) {
//            System.out.println("""
//                    ==========================
//                    0 | Previous Menu
//                    1 | View Transactions
//                    2 | View Goals
//                    3 | View Categories
//                    """);
//            String userInput = scanner.next();
//            switch (userInput) {
//                case "0" -> menuOpen = false;
//                case "1" -> ListPrinters.printTransactionList(DB_Transaction.getTransactionList(user.getUserId()));
//                case "2" -> ListPrinters.printGoalsTable(DB_Goal.getGoalList(user.getUserId()));
//                case "3" -> ListPrinters.printCategoryList(DB_Category.getCategoryList(user.getUserId()));
//                default -> {
//                }
//            }
//        }
//
//    }
}
