package FileManagers;

import java.io.*;
import java.util.LinkedList;

/**
 * Represents an expense with a name and an amount.
 */
class Expense {
    private String name;
    private double amount;

    /**
     * Constructs an Expense object with the given name and amount.
     * @param name The name of the expense.
     * @param amount The amount of the expense.
     */
    public Expense(String name, double amount) {
        this.name = name;
        this.amount = amount;
    }

    /**
     * Sets the name of the expense.
     * @param name The name to set.
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Sets the amount of the expense.
     * @param amount The amount to set.
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * Retrieves the name of the expense.
     * @return The name of the expense.
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the amount of the expense.
     * @return The amount of the expense.
     */
    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return name + ": " + amount;
    }
}

/**
 * Manages expenses by reading from and writing to a CSV file.
 */
public class ExpensesFileManager {
    private final String filePath = "C:\\Users\\afifs\\OneDrive\\Desktop\\Project\\CS2043Project\\data\\expenses.csv";
    private LinkedList<Expense> expenses;

    /**
     * Constructs a new ExpensesFileManager and reads expenses from the file.
     */
    public ExpensesFileManager() {
        expenses = readExpenses();
    }

    /**
     * Reads expenses from the CSV file.
     * @return A LinkedList containing the read expenses.
     */
    private LinkedList<Expense> readExpenses() {
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

    /**
     * Writes an expense to the CSV file and adds it to the list of expenses.
     * @param name The name of the expense.
     * @param amount The amount of the expense.
     */
    public void writeExpenses(String name, double amount) {
        Expense expense = new Expense(name, amount);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(name + "," + amount + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        expenses.add(expense);
        sortExpenses(expenses);
    }

    /**
     * Sorts the expenses list in ascending order by amount.
     * @param expenses The list of expenses to sort.
     */
    private void sortExpenses(LinkedList<Expense> expenses) {
        expenses.sort((expense1, expense2) -> Double.compare(expense1.getAmount(), expense2.getAmount()));
    }

    /**
     * Prints the list of expenses to the console.
     */
    public void printList() {
        System.out.println("List of Expenses:");
        for (Expense expense : expenses) {
            System.out.println(expense);
        }
    }
}
