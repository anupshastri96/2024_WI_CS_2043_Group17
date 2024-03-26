package ags.project;
import Database.*;
import Objects.*;
import Enum.*;

import java.time.LocalDate;
import java.util.Date;

import static java.lang.System.out;

public class Main{

    public static void main(String[] args) {
        Date date = new Date();
        //DB_User.addUser("afifsaba", "ags117","afifsaba53@gmail.com");
        User user = DB_User.getUser(2);
        java.util.Date utilDate = new java.util.Date(); // Replace this with your actual java.util.Date object
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        DB_Transaction.addTransaction(2, sqlDate, "Test 1", 'w', 100, "This is a test", "Food");

        //User temp = DB_User.getUser(1);
        System.out.println(user.getUsername() + " " + user.getEmail());
//        User afif = new User("afifsaba", "ags117","afifsaba53@gmail.com");
//        Category utility = new Category("Utility's", 100);
//        Category food = new Category("Food", 300);
//        afif.getBudgetTracker().addCategory(utility);
//        afif.getBudgetTracker().addCategory(food);
//
//        afif.getBudgetTracker().addTransaction(new Transaction("test1", 'w', 9999, utility));
//        afif.getBudgetTracker().addTransaction(new Transaction("test2", 'd', 9999, food));
//        afif.getBudgetTracker().addTransaction(new Transaction("test3", 'w', 9999, food));
//        afif.getBudgetTracker().addTransaction(new Transaction("test4", 'w', 9999, food));
//
//        afif.getBudgetTracker().addExpense(new Expense("Expense1", 100, Term.WEEKLY,utility));
//        afif.getBudgetTracker().addExpense(new Expense("Expense2", 200, Term.MONTHLY, food));
//        afif.getBudgetTracker().addExpense(new Expense("Expense3", 300, Term.BIWEEKLY, utility));
//        afif.getBudgetTracker().addExpense(new Expense("Expense4", 400, Term.YEARLY, utility));
//
//        afif.getBudgetTracker().printTransactionList();
//        afif.getBudgetTracker().printExpenseList();
//        afif.getBudgetTracker().printCategoryList();

    }
}