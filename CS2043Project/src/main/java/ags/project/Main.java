package ags.project;
import FileManagers.ExpensesFileManager;
import FileManagers.IncomeFileManager;
import FileManagers.TransactionFileManager;

import java.io.IOException;
import static FileManagers.IncomeFileManager.*;

public class Main{
    public static void main(String[] args) throws IOException {

        TransactionFileManager transactionList = new TransactionFileManager();
        transactionList.printList();
        TransactionFileManager.writeTransaction("Test3", 'w', 2, "this is");
        transactionList.printList();

        IncomeFileManager.readIncome();
        ExpensesFileManager expenseList = new ExpensesFileManager();
        expenseList.printList();

    }
}