package Objects;

import Database.DB_Goal;

import java.util.LinkedList;

public class ListPrinters {
    public static void printCategoryList(LinkedList<Category> categoryLinkedList) {
        System.out.println("Categories Overview");
        System.out.println("-----------------------------------------------------------------");
        System.out.printf("%-20s %-20s %-20s\n", "Category Name", "Budget", "Transactions Count");
        System.out.println("-----------------------------------------------------------------");

        for (Category category : categoryLinkedList) {
            int transactionsCount = category.getTransactions().size();
            System.out.printf("%-20s %-20.2f %-20d\n", category.getName(), category.getBudget(), transactionsCount);
        }

        System.out.println("-----------------------------------------------------------------");
    }


    /**
     * Prints a list of all transactions, including their details such as date, ID, name, type, amount, and description.
     */
    public static void printTransactionList(LinkedList<Transaction> transactionLinkedList) {
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-15s | %-10s | %-50s | %-4s | %-8s | %-225s | %-50s |%n", "Transaction ID", "Date", "Name", "Type", "Amount", "Description" , "Category");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
        for (Transaction transaction : transactionLinkedList) {
            System.out.printf("| %-15s | %-10s | %-50s | %-4s | %-7.2f | %-225s | %-50s |%n",
                    transaction.getTransactionId(),
                    transaction.getDate(),
                    transaction.getName(),
                    transaction.getType(),
                    transaction.getAmount(),
                    transaction.getDescription(),
                    transaction.getCategory().getName()
            );
        }
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
    }
    public static void printGoalsTable(LinkedList<Goal> goalsList) {
        // Print table header
        System.out.println("+--------+----------------------+--------------+--------------+------------+");
        System.out.println("| User ID|         Name         | Amount Saved | Total Amount |    Date    |");
        System.out.println("+--------+----------------------+--------------+--------------+------------+");

        // Print each goal
        for (Goal goal : goalsList) {
            System.out.printf("| %6d | %-20s | %12.2f | %12.2f | %-10s |%n",
                    goal.getUserid(), goal.getGoalName(), goal.getGoalAmtCollected(), goal.getGoalTotalAmt(), goal.getDate());
        }

        // Print table footer
        System.out.println("+--------+----------------------+--------------+--------------+------------+");
    }
}


