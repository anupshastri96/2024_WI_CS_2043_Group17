package MiniProject;

public class Category {
    private static int categoryId = 0;
    private String categoryName;
    private String categoryDescription;

    public Category(String categoryName, String categoryDescription) {
        categoryId++;
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
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

    public static int getCategoryId() {
        return categoryId;
    }

    public String toString(){
        return categoryId + "\t" + categoryName + "\t" + categoryDescription;
    }
}
