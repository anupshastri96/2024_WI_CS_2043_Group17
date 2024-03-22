package MiniProject;

import java.util.ArrayList;

public class TransactionList{
    private ArrayList<Transaction> list;
    private ArrayList<Transaction> expense;
    private ArrayList<Transaction> income;
    private final User client;

    public TransactionList(User client){
        this.client = client;
        list = new ArrayList<>(1);
        expense = new ArrayList<>(1);
        income = new ArrayList<>(1);
    }

    public void categorize(){
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getType() == 'I'){       //we can decide the letter type later
                income.add(list.get(i));
            }
            else{
                expense.add(list.get(i));
            }
        }
    }

    public String getAllTransaction(){
        String toReturn = "";
        for (Transaction trans : list){
            toReturn += trans.toString() + "\n";
        }
        return toReturn;
    }

    public String getIncome(){
        String toReturn = "";
        for (Transaction inc : income){
            toReturn += inc.toString() + "\n";
        }
        return toReturn;
    }

    public String getExpense(){
        String toReturn = "";
        for (Transaction exp : expense){
            toReturn += exp.toString() + "\n";
        }
        return toReturn;
    }

    public void setList(ArrayList<Transaction> list){
        this.list = list;
    }

    public User getClient(){
        return client;
    }

    public void addTransaction(Transaction trans){
        list.add(trans);
    } 

    public void addTransaction(String name, char type, double amount, String description){
        Transaction trans = new Transaction(name, type, amount, description);
        list.add(trans);
    }

    public void removeTransaction(Transaction trans){
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getTransID() == trans.getTransID()){
                list.remove(i);
            }
        }
    } 

    public void removeTransaction(int transID){
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getTransID() == transID){
                list.remove(i);
            }
        }
    }

    public void editTransaction(){
        
    }

    //addGoal()
    //removeGoal()
    //editGoal()
    //setReminder()
    //generateReport
}
