package Lists;

import Objects.Category;

import java.util.LinkedList;

/**
 * Represents a list of categories, where each category is associated with a budget and transactions.
 * Allows for adding new categories and printing a summary of all categories.
 */
public class CategoryList {
    private LinkedList<Category> categoryList;

    /**
     * Initializes a new, empty list of categories.
     */
    public CategoryList() {
        categoryList = new LinkedList<>();
    }

    /**
     * Gets the list of categories.
     *
     * @return A LinkedList of Category objects.
     */
    public LinkedList<Category> getCategoryList() {
        return categoryList;
    }

    /**
     * Sets the list of categories to a new list.
     *
     * @param categoryList The new list of Category objects.
     */
    public void setCategoryList(LinkedList<Category> categoryList) {
        this.categoryList = categoryList;
    }

    /**
     * Adds a new category with the specified name and budget amount.
     *
     * @param name The name of the new category.
     * @param amount The budget amount for the new category.
     */
    public void addCategory(String name, double amount){
        categoryList.add(new Category(name, amount));
    }

    /**
     * Adds an existing Category object to the list.
     *
     * @param category The Category object to add.
     */
    public void addCategory(Category category){
        categoryList.add(category);
    }

    /**
     * Prints a summary of all categories, including their names, budget amounts, and the number of transactions.
     */
    public void printList() {
        System.out.println("Categories Overview");
        System.out.println("-----------------------------------------------------------------");
        System.out.printf("%-20s %-20s %-20s\n", "Category Name", "Budget", "Transactions Count");
        System.out.println("-----------------------------------------------------------------");

        for (Category category : categoryList) {
            int transactionsCount = category.getTransactions().getTransactionList().size();
            System.out.printf("%-20s %-20.2f %-20d\n", category.getName(), category.getBudget(), transactionsCount);
        }

        System.out.println("-----------------------------------------------------------------");
    }
}
