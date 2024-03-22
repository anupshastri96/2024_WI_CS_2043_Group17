package MiniProject;

import java.text.NumberFormat;

public class Goal{
    private String goalName;
    private final double goalTotalAmt;
    private final int goalDuration; 
    private double goalMonthlyAmt;

    public Goal(String goalName, double goalTotalAmt, int goalDuration){
        this.goalName = goalName;
        this.goalTotalAmt = goalTotalAmt;
        this.goalDuration = goalDuration;
        goalMonthlyAmt = goalTotalAmt / (goalDuration * 12);
    }

    public String getGoalName(){
        return goalName;
    }

    public double getGoalTotalAmt(){
        return goalTotalAmt;
    }

    public int getGoalDuration(){
        return goalDuration;
    }

    public double getGoalMonthlyAmt(){
        return goalMonthlyAmt;
    }

    public void setGoalName(String goalName){
        this.goalName = goalName;
    }
    
    public String toString(){
        NumberFormat fm = NumberFormat.getCurrencyInstance();
        return goalName + " in " + goalDuration + "years: " 
                + fm.format(goalTotalAmt) + "(" + fm.format(goalMonthlyAmt) + "/monthly)";
    }
}