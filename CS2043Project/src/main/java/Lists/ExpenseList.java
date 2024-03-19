package Lists;

import java.util.LinkedList;
import Enum.Term;
import Objects.Expense;
import Objects.Transaction;

public class ExpenseList{
    private LinkedList<Expense> expenseList;
    public ExpenseList(){
        expenseList = new LinkedList<>();
    }

    public LinkedList<Expense> getExpenseList() {
        return expenseList;
    }

    public void setExpenseList(LinkedList<Expense> expenseList) {
        this.expenseList = expenseList;
    }

    public void addExpense(String name, double amount, String description, Term term) {
        Expense expense = new Expense(name, amount, description, term);
        expenseList.add(expense);
    }
    public void addExpense(String name, double amount, Term term) {
        Expense expense = new Expense(name, amount, "", term);
        expenseList.add(expense);
    }
    public void printList() {
        System.out.printf("------------------------------------------------------------------------------------------------------------------------%n");
        System.out.printf("| %-10s | %-15s | %-8s | %-10s | %-8s | %-50s |%n", "Date", "Expense ID", "Name", "Term", "Amount", "Description");
        System.out.printf("------------------------------------------------------------------------------------------------------------------------%n");
        for (Expense expense : expenseList) {
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
}
