package Objects;

import java.text.NumberFormat;
import java.util.Date;

public class Goal {
    private int userid;
    private String goalName;
    private double goalAmtCollected;
    private final double goalTotalAmt;
    private Date date;

    public Goal(int userid, String goalName,double goalAmtCollected, double goalTotalAmt, Date date) {
        this.userid = userid;
        this.goalName = goalName;
        this.goalAmtCollected = goalAmtCollected;
        this.goalTotalAmt = goalTotalAmt;
        this.date = date;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getGoalName() {
        return goalName;
    }

    public double getGoalTotalAmt() {
        return goalTotalAmt;
    }


    public void setGoalName(String goalName) {
        this.goalName = goalName;
    }

    public double getGoalAmtCollected() {
        return goalAmtCollected;
    }

    public void setGoalAmtCollected(double goalAmtCollected) {
        this.goalAmtCollected = goalAmtCollected;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}