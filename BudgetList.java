package MiniProject;

import java.util.ArrayList;

public class BudgetList {
    private ArrayList<Budget> bgList;
    //private final User client;

    public BudgetList(){
        bgList = new ArrayList<>(1);
    }

    public void addBuget(Budget toAdd){
        bgList.add(toAdd);
    }

    public void addBudget(String budgetName, double budgetAmount, String budgetDescription, CategoryList budgetCategories){
        Budget toAdd = new Budget(budgetName, budgetAmount, budgetDescription, budgetCategories);
        bgList.add(toAdd);
    }

    public void removeBudget(int bgID){
        //bgList.remove(bgID-1);
        for(int i = 0; i<bgList.size(); i++){
            if(bgList.get(i).getBudgetID() == bgID){
                bgList.remove(i);
            }
        }
    }

    public void removeBudget(String name){
        for(int i = 0; i<bgList.size(); i++){
            if(bgList.get(i).getBudgetName().equals(name)){
                bgList.remove(i);
            }
        }
    }

    public void editBudget(int bgID, String newBudgetName, double newBudgetAmount, String newBudgetDescription, CategoryList newBudgetCategories){
        for(int i = 0; i<bgList.size(); i++){
            if(bgList.get(i).getBudgetID() == bgID){
                bgList.get(i).setBudgetName(newBudgetName);
                bgList.get(i).setBudgetAmount(newBudgetAmount);
                bgList.get(i).setBudgetDescription(newBudgetDescription);
            }
        }
    }

    public void editBudget(String name, String newBudgetName, double newBudgetAmount, String newBudgetDescription, CategoryList newBudgetCategories){
        for(int i = 0; i<bgList.size(); i++){
            if(bgList.get(i).getBudgetName().equals(name)){
                bgList.get(i).setBudgetName(newBudgetName);
                bgList.get(i).setBudgetAmount(newBudgetAmount);
                bgList.get(i).setBudgetDescription(newBudgetDescription);
            }
        }
    }

    public String toString(){
        String toReturn = "";
        for (Budget bg : bgList){
            toReturn += bg.toString() + "\n";
        }
        return toReturn;
    }
}