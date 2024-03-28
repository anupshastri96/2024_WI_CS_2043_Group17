package ags.project;

import Database.*;
import Objects.*;

import java.io.Console;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Interface {
    private static User user = null;
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        Console console = System.console();
        boolean successfulLogin = false;
        while(user == null) {
            System.out.println("Enter your username and password.");
            System.out.print("Username: ");
            String username = scan.next();
            System.out.print("Password: ");
            String password = scan.next();
            System.out.println("Attempting login...");
            user = User.login(username, password, DB_User.getUserList());
            if (user == null) {
                System.err.println("Username or Password incorrect");
            }
        }

        String commandList = "Command list:\n" +
                "/addTransaction - Add a new transaction\n" +
                "/editTransaction - Edit an existing transaction\n" +
                "/removeTransaction - Remove a transaction\n" +
                "/viewTransactionList - View a list of your transactions\n" +
                "/addGoal - Add a saving goal\n" +
                "/editGoal - Edit a goal\n" +
                "/removeGoal - Remove a goal\n" +
                "/viewGoalList - Veiw your current goals";

        String dashboard = "\n-----DASHBOARD-----";
        System.out.println("Login successful, Welcome " + user.getUsername());
        System.out.println("-----DASHBOARD-----");
        System.out.println("For a list of commands: /commands");
        boolean logout = false;
        while(!logout){

            String command = scan.next();

            if(command.equals("/addTransaction")){
                System.out.println("Please input the following information: ");
                System.out.print("Name of Transaction: ");
                String name = scan.next();
                char type = ' ';
                boolean temp = true;
                while(temp) {
                    System.out.print("Type(W - withdraw / D - deposit): ");
                    type = scan.next().charAt(0);
                    type = Character.toUpperCase(type);
                    if (type != 'W' && type != 'D') {
                        System.out.println();
                        System.err.println("Incorrect type! Please enter 'W' or 'D'.");
                    }
                    else {
                        temp = false;
                    }
                }

                System.out.print("Transaction amount: $");
                double amount = scan.nextDouble();
                System.out.print("Description(Optional):");
                String description = scan.next();
                System.out.print ("What category does this transaction belong to?:");
                String category = scan.next();
                if(!DB_Category.doesCategoryExist(user.getUserId(), category)){
                    System.err.println("Category does not exist!");
                    System.out.println(dashboard);
                }
                else{
                    DB_Transaction.addTransaction(user.getUserId(), name, type, amount, description, category);
                    System.out.println("Transaction added.");
                    System.out.println(dashboard);
                }

            }

            else if(command.equals("/editTransaction")){
                LinkedList<Transaction> transactionLinkedList = DB_Transaction.getTransactionList(user.getUserId());
                ListPrinters.printTransactionList(transactionLinkedList);
                System.out.print("Enter transaction ID: ");
                int transactionId = scan.nextInt();
                System.out.println("Finding transaction...");
                if(Transaction.doesTransactionExist(transactionId, transactionLinkedList)){
                    System.out.println("Transaction Found");
                    System.out.println("Please input the following information: ");
                    System.out.print("Name of Transaction: ");
                    String name = scan.next();
                    char type = ' ';
                    boolean temp = true;
                    while(temp) {
                        System.out.print("Type(W - withdraw / D - deposit): ");
                        type = scan.next().charAt(0);
                        type = Character.toUpperCase(type);
                        if (type != 'W' && type != 'D') {
                            System.out.println();
                            System.err.println("Incorrect type! Please enter 'W' or 'D'.");
                        }
                        else {
                            temp = false;
                        }
                    }

                    System.out.print("Transaction amount: $");
                    double amount = scan.nextDouble();
                    System.out.print("Description(Optional):");
                    String description = scan.next();
                    System.out.print ("What category does this transaction belong to?:");
                    String category = scan.next();
                    if(!DB_Category.doesCategoryExist(user.getUserId(), category)){
                        System.err.println("Category does not exist!");
                    }
                    else{
                        DB_Transaction.addTransaction(user.getUserId(), name, type, amount, description, category);
                        System.out.println("Transaction added.");
                        System.out.println(dashboard);
                    }
                }
                else{
                    System.err.println("Transaction does not exist.");
                    System.out.println(dashboard);
                }


            }

            else if(command.equals("/removeTransaction")){
                LinkedList<Transaction> transactionLinkedList = DB_Transaction.getTransactionList(user.getUserId());
                ListPrinters.printTransactionList(transactionLinkedList);
                System.out.print("Enter transaction ID: ");
                int transactionId = scan.nextInt();
                System.out.println("Finding transaction...");
                if(Transaction.doesTransactionExist(transactionId, transactionLinkedList)){
                    System.out.println("Transaction Found");
                    DB_Transaction.deleteTransaction(transactionId);
                    System.out.println("deleted");
                    System.out.println(dashboard);
                }
                else{
                    System.err.println("Transaction does not exist.");
                    System.out.println(dashboard);
                }
            }

            else if(command.equals("/viewTransactionList")){
                ListPrinters.printTransactionList(DB_Transaction.getTransactionList(user.getUserId()));
                System.out.println(dashboard);



            }

            else if(command.equals("/addGoal")){

            }

            else if(command.equals("/editGoal")){

            }

            else if(command.equals("/removeGoal")){

            }
            else if (command.equals("/commands")) {
                System.out.println(commandList);


            }

            else if (command.equals("/logout")) {
                System.out.println("Logging out...");
                logout = true;

            }

            else{
                System.out.println("Command not found.");
            }
        }
        System.out.println("Logout Successful. Goodbye " + user.getUsername());
    }


}

