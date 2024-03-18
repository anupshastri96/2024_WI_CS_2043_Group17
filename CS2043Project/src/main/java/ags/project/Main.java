package ags.project;
import FileManagers.ExpensesFileManager;
import FileManagers.IncomeFileManager;
import FileManagers.TransactionFileManager;
import Lists.TransactionList;

import java.io.IOException;
import static FileManagers.IncomeFileManager.*;

public class Main{
    public static void main(String[] args) throws IOException {

        User afif = new User("afifsaba", "ags117","afifsaba53@gmail.com");
        TransactionList transactionList = new TransactionList();
        afif.getTransactionList().addTransaction("test1", 'w', 9999);
        afif.getTransactionList().addTransaction("test2", 'd', 9999);
        afif.getTransactionList().addTransaction("test3", 'w', 9999);
        afif.getTransactionList().addTransaction("test4", 'w', 9999);
        afif.getTransactionList().printList();

    }
}