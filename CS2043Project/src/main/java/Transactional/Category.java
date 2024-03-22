package Transactional;

import java.io.Serializable;
import java.util.Objects;

public class Category implements Serializable {
    private final int categoryId;
    private String categoryName;
    private String categoryDescription;
    private double budgetAmount;

    public Category(int categoryId, String categoryName, String categoryDescription, double budgetAmount) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
        this.budgetAmount = budgetAmount;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public double getBudgetAmount() {
        return budgetAmount;
    }

    public void setBudgetAmount(double budgetAmount) {
        this.budgetAmount = budgetAmount;
    }

    public boolean isOverBudget(double amountSpent){
        return amountSpent > budgetAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return categoryId == category.categoryId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryId);
    }
}
