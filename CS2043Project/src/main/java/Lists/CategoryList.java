package Lists;

import Objects.Category;
import Objects.Transaction;

import java.util.LinkedList;

public class CategoryList {
    private LinkedList<Category> categoryList;

    public CategoryList() {
        categoryList = new LinkedList<>();
    }

    public LinkedList<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(LinkedList<Category> categoryList) {
        this.categoryList = categoryList;
    }
    public void addCategory(String name, double amount){
        categoryList.add(new Category(name, amount));
    }
    public void addCategory(Category category){
        categoryList.add(category);
    }

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
