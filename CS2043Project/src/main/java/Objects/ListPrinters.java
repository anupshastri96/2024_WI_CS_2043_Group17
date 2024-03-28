package Objects;

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
     * Prints a list of all expenses, including their details such as date, ID, name, term, amount, and description.
     */
    public static void printExpenseList(LinkedList<Expense> expenseLinkedList) {
        System.out.printf("------------------------------------------------------------------------------------------------------------------------%n");
        System.out.printf("| %-10s | %-15s | %-8s | %-10s | %-8s | %-50s |%n", "Date", "Expense ID", "Name", "Term", "Amount", "Description");
        System.out.printf("------------------------------------------------------------------------------------------------------------------------%n");
        for (Expense expense : expenseLinkedList) {
            System.out.printf("| %-10s | %-15d | %-8s | %-10s | %-8.2f | %-50s |%n",
                    expense.getDate(),
                    expense.getTransactionId(),
                    expense.getName(),
                    expense.getTerm().name(),
                    expense.getAmount(),
                    expense.getDescription()
            );
        }
        System.out.printf("------------------------------------------------------------------------------------------------------------------------%n");
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
}


