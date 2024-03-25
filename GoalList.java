package MiniProject;

import java.util.ArrayList;

public class GoalList{
    //private User client;
    private ArrayList<Goal> goalList;

    public GoalList(){
        goalList = new ArrayList<>(1);
    }

    public String toString(){
        String toReturn = "";
        for(Goal g : goalList){
            toReturn += g.toString() + "\n";
        }
        return toReturn;
    }

    public void addGoal(Goal toAdd){
        goalList.add(toAdd);
    }

    public void addGoal(String name, double amount, int duration){
        Goal toAdd = new Goal(name, amount, duration);
        goalList.add(toAdd);
    }

    public void removeGoal(String name){
        for(int i = 0; i < goalList.size(); i++){
            if(goalList.get(i).getGoalName().equals(name)){
                goalList.remove(i);
            }
        }
    }
}