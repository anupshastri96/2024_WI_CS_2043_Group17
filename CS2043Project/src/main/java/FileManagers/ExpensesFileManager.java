package FileManagers;

import java.io.*;
import java.util.LinkedList;

class Expense {
    private String name;
    private double amount;

    public Expense(String nameIn, double amountIn) {
        name = nameIn;
        amount = amountIn;
    }

    public void setName(String name){
        this.name = name;

    }
    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return name + ": " + amount;
    }
}


public class ExpensesFileManager {
    private static final String filePath = "C:\\Users\\afifs\\OneDrive\\Desktop\\Project\\CS2043Project\\data\\expenses.csv";
    private static LinkedList<Expense> expenses;

    /**
     * Creates a list. Please remember to reinitialize this object to refresh the list.
     */
    public ExpensesFileManager(){
        expenses = readExpenses();
    }


    private static LinkedList<Expense> readExpenses() {
        LinkedList<Expense> tempExpenses = new LinkedList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String expenseName = parts[0].trim();
                    double expenseAmount = Double.parseDouble(parts[1].trim());
                    tempExpenses.add(new Expense(expenseName, expenseAmount));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        sortExpenses(tempExpenses);
        return tempExpenses;
    }


    public static void writeExpenses(String name, double amount) {
        Expense expense = new Expense(name, amount);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(name + "," + amount + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        expenses.add(expense); // Add the new expense to the list
        sortExpenses(expenses);
    }

    public static void sortExpenses(LinkedList<Expense> expenses) {
        expenses.sort((expense2, expense1) -> Double.compare(expense1.getAmount(), expense2.getAmount()));
    }

    public String printList() {
        String string = "";
        string += expenses.toString() + "\n";
        return string;
    }
}
