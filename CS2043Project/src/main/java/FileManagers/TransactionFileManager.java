package FileManagers;

import java.io.*;
import java.util.LinkedList;

class Transaction {
    private String name;
    private char type;
    private double amount;

    public Transaction(String nameIn, char typeIn, double amountIn) {
        name = nameIn;
        type = typeIn;
        amount = amountIn;

    }

    public void setName(String name) {
        this.name = name;

    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setType(char type) {
        this.type = type;
    }
    public String getName(){
        return name;
    }

    public char getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return name + ", " + type + ", " + amount;
    }
}
public class TransactionFileManager {
    private static final String filePath = "C:\\Users\\afifs\\OneDrive\\Desktop\\OneDrive - University of New Brunswick\\School\\Winter 2024\\CS3103\\CS2043\\Project\\CS2043Project\\data\\transactions.csv";

    private static LinkedList<Transaction> transactions;
    public TransactionFileManager(){
        transactions = readTransaction();
    }
    private static LinkedList<Transaction> readTransaction() {
        LinkedList<Transaction> tempTransactions = new LinkedList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String transactionName = parts[0].trim();
                    char transactionType = parts[1].charAt(0);
                    double transactionAmount = Double.parseDouble(parts[2].trim());

                    tempTransactions.add(new Transaction(transactionName, transactionType, transactionAmount));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tempTransactions;
    }

    public static void writeTransaction(String name, char type, double amount) {
        Transaction transaction = new Transaction(name, type ,amount);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(name + "," + type + "," + amount + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        transactions.add(transaction);
    }
}
