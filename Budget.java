package MiniProject;

public class Budget {
    private static int budgetID = 0;
    private String budgetName;
    private double budgetAmount;
    private String budgetDescription;
    private CategoryList budgetCategories;
    //private ArrayList<Category> budgetCategories;

    public Budget(String budgetName, double budgetAmount, String budgetDescription, CategoryList budgetCategories){
        budgetID++;
        this.budgetName = budgetName;
        this.budgetAmount = budgetAmount;
        this.budgetDescription = budgetDescription;
        this.budgetCategories = budgetCategories;
    }

    public static int getBudgetID(){
        return budgetID;
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

    //public void addCategory(Category category) {
    //    budgetCategories.add(category);
    //}

    public String toString(){
        return budgetName + "\t" + budgetAmount + "\t" + budgetDescription + "\t" + budgetCategories.toString();
    }
}
