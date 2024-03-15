package FileManagers;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class BudgetFileManager {

    public static ArrayList<Budget> readInBudgets(){
        ArrayList<Budget> budgetList = null;
        try {
            FileInputStream budgetFile = new FileInputStream(System.getProperty("user.dir") + "\\CS2043Project\\data\\budgets.bin");
            ObjectInputStream budgetIn = new ObjectInputStream(budgetFile);

            budgetList = (ArrayList<Budget>) budgetIn.readObject();

            budgetIn.close();
        } catch (FileNotFoundException e) {
            return budgetList;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return budgetList;
    }

    public static void writeOutBudgets(ArrayList<Budget> budgetList){
        try {
            FileOutputStream budgetFile = new FileOutputStream(System.getProperty("user.dir") + "\\CS2043Project\\data\\budgets.bin");
            ObjectOutputStream budgetOut = new ObjectOutputStream(budgetFile);

            budgetOut.writeObject(budgetList);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
