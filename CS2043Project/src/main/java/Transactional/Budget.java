package Transactional;

import Transactional.Category;

import java.util.ArrayList;

public class Budget {
    private String budgetName;
    private double budgetAmount;
    private String budgetDescription;
    private ArrayList<Category> budgetCategories;

    public Budget(String budgetName, double budgetAmount, String budgetDescription, ArrayList<Category> budgetCategories) {
        this.budgetName = budgetName;
        this.budgetAmount = budgetAmount;
        this.budgetDescription = budgetDescription;
        this.budgetCategories = budgetCategories;
    }

    public String getBudgetName() {
        return budgetName;
    }

    public void setBudgetName(String budgetName) {
        this.budgetName = budgetName;
    }

    public double getBudgetAmount() {
        return budgetAmount;
    }

    public void setBudgetAmount(double budgetAmount) {
        this.budgetAmount = budgetAmount;
    }

    public String getBudgetDescription() {
        return budgetDescription;
    }

    public void setBudgetDescription(String budgetDescription) {
        this.budgetDescription = budgetDescription;
    }

    public void addCategory(Category category) {
        budgetCategories.add(category);
    }
}
