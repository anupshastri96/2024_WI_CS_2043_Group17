package ags.project;
import Objects.*;
import Enum.*;

public class Main{
    public static void main(String[] args) {

        User afif = new User("afifsaba", "ags117","afifsaba53@gmail.com");
        Category utility = new Category("Utility's", 100);
        Category food = new Category("Food", 300);
        afif.getBudgetTracker().addCategory(utility);
        afif.getBudgetTracker().addCategory(food);

        afif.getBudgetTracker().addTransaction(new Transaction("test1", 'w', 9999, utility));
        afif.getBudgetTracker().addTransaction(new Transaction("test2", 'd', 9999, food));
        afif.getBudgetTracker().addTransaction(new Transaction("test3", 'w', 9999, food));
        afif.getBudgetTracker().addTransaction(new Transaction("test4", 'w', 9999, food));

        afif.getBudgetTracker().addExpense(new Expense("Expense1", 100, Term.WEEKLY,utility));
        afif.getBudgetTracker().addExpense(new Expense("Expense2", 200, Term.MONTHLY, food));
        afif.getBudgetTracker().addExpense(new Expense("Expense3", 300, Term.BIWEEKLY, utility));
        afif.getBudgetTracker().addExpense(new Expense("Expense4", 400, Term.YEARLY, utility));

        afif.getBudgetTracker().printTransactionList();
        afif.getBudgetTracker().printExpenseList();
        afif.getBudgetTracker().printCategoryList();

    }
}