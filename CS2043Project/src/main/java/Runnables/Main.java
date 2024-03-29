package Runnables;
import Database.*;
import Objects.*;
import Enum.*;
import com.mysql.cj.protocol.a.LocalDateTimeValueEncoder;

import java.util.Scanner;

import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedList;

import static java.lang.System.out;

public class Main{

    public static void main(String[] args) {
        DB_User.addUser("test", "dhehd", "jfenfe", 9999);
//        //DB_User.addUser("ags1", "ags117","test@gmail.com");
//        Scanner scan = new Scanner(System.in);
//        System.out.println("Enter your username and password.");
//        System.out.print("Username: ");
//        String username = scan.next();
//        System.out.print("Password: ");
//        String password = scan.next();
//        User user = User.login(username,password,DB_User.getUserList());
//        if(user == null){
//            System.err.print("Username or Password incorrect");
//            System.exit(1);
//        }







//        System.out.println("userid: " + user.getUserId());
//        System.out.println("username: " + user.getUsername());
//        System.out.println("email: " + user.getEmail());
//        user.getBudgetTracker().printTransactionList();
//        System.out.println();
//        user.getBudgetTracker().printCategoryList();




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