package ags.project;
import Objects.*;
import Lists.*;
import Enum.*;

import java.io.IOException;

public class Main{
    public static void main(String[] args) throws IOException {

        User afif = new User("afifsaba", "ags117","afifsaba53@gmail.com");
        TransactionList transactionList = new TransactionList();
        afif.getTransactionList().addTransaction("test1", 'w', 9999);
        afif.getTransactionList().addTransaction("test2", 'd', 9999);
        afif.getTransactionList().addTransaction("test3", 'w', 9999);
        afif.getTransactionList().addTransaction("test4", 'w', 9999);
        afif.getExpenseList().addExpense("Expense1", 100, Term.WEEKLY);
        afif.getExpenseList().addExpense("Expense2", 200, Term.MONTHLY);
        afif.getExpenseList().addExpense("Expense3", 300, Term.BIWEEKLY);
        afif.getExpenseList().addExpense("Expense4", 400, Term.YEARLY);
        afif.getTransactionList().printList();
        afif.getExpenseList().printList();

    }
}