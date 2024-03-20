package ags.project;
import Objects.*;
import Lists.*;
import Enum.*;

import java.io.IOException;

public class Main{
    public static void main(String[] args) {

        User afif = new User("afifsaba", "ags117","afifsaba53@gmail.com");
        TransactionList transactionList = new TransactionList();
        CategoryList categoryList = new CategoryList();
        Category utility = new Category("Utility's", 100);
        Category food = new Category("Food", 300);
        categoryList.addCategory(utility);
        categoryList.addCategory(food);

        afif.getTransactionList().addTransaction("test1", 'w', 9999, utility);
        afif.getTransactionList().addTransaction("test2", 'd', 9999, food);
        afif.getTransactionList().addTransaction("test3", 'w', 9999, food);
        afif.getTransactionList().addTransaction("test4", 'w', 9999, food);

        afif.getExpenseList().addExpense("Expense1", 100, Term.WEEKLY,utility);
        afif.getExpenseList().addExpense("Expense2", 200, Term.MONTHLY, food);
        afif.getExpenseList().addExpense("Expense3", 300, Term.BIWEEKLY, utility);
        afif.getExpenseList().addExpense("Expense4", 400, Term.YEARLY, utility);
        categoryList.printList();
        afif.getTransactionList().printList();
        afif.getExpenseList().printList();

    }
}