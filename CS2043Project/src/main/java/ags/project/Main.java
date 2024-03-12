package ags.project;
import FileManagers.ExpensesFileManager;
import FileManagers.IncomeFileManager;

import java.io.IOException;
import static FileManagers.IncomeFileManager.*;

public class Main{
    public static void main(String[] args) throws IOException {

        System.out.println(IncomeFileManager.readIncome());
        IncomeFileManager.writeIncome(20000);

        ExpensesFileManager expenseList = new ExpensesFileManager();
        System.out.print(expenseList.printList());




    }
}