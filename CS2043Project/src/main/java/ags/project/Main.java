package ags.project;
import Database.*;
import Objects.*;
import Enum.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedList;

import static java.lang.System.out;

public class Main{

    public static void main(String[] args) {
        Date date = new Date();
        //DB_User.addUser("ags1", "ags117","test@gmail.com");
        User user = DB_User.getUser(2);
        //DB_Category.addCategory(user.getUserId(), "testCategory1", 999);

        System.out.println("userid: " + user.getUserId());
        System.out.println("username: " + user.getUsername());
        System.out.println("email: " + user.getEmail());
        user.getBudgetTracker().printTransactionList();
        System.out.println();
        user.getBudgetTracker().getCategoryLinkedList();




    }
    /*public static String transactionsToString(LinkedList<Transaction> transactions) {
        StringBuilder sb = new StringBuilder();
        for (Transaction transaction : transactions) {
            sb.append("Transaction ID: ").append(transaction.getTransactionId()).append(", ");
            sb.append("Date: ").append(transaction.getDate()).append(", ");
            sb.append("Name: ").append(transaction.getName()).append(", ");
            sb.append("Type: ").append(transaction.getType()).append(", ");
            sb.append("Amount: ").append(transaction.getAmount()).append(", ");
            sb.append("Description: ").append(transaction.getDescription()).append(", ");
            sb.append("Category: ").append(transaction.getCategory().getName()).append("\n");
        }
        return sb.toString();
    }*/

}