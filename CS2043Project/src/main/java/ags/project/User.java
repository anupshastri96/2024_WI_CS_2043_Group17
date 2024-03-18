package ags.project;

//import Objects.Budget;

import Lists.CategoryList;
import Lists.TransactionList;

public class User{
    private int userId;
    private String username;
    protected String password;
    private String email;
    public TransactionList transactionList;
    public CategoryList categoryList;



    public User(String username, String password, String email){
        this.username = username;
        this.password = password;
        this.email = email;
        transactionList = new TransactionList();
        categoryList = new CategoryList();
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public TransactionList getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(TransactionList transactionList) {
        this.transactionList = transactionList;
    }

    public CategoryList getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(CategoryList categoryList) {
        this.categoryList = categoryList;
    }

    public boolean login(String username, String password) {
        if(this.username. equals(username) && this.password.equals(password)){
            return true;
        }
        else{
            return false;
        }
    }
    public boolean changePassword(String oldPassword, String newPassword) {
        if (this.password.equals(oldPassword)) {
            this.password = newPassword;
            return true;

        }
        else {
            return false;
        }
    }
}
