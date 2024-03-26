package Objects;

import java.text.NumberFormat;

public class Goal {
    private int userid;
    private String goalName;
    private final double goalTotalAmt;

    public Goal(int userid, String goalName, double goalTotalAmt) {
        this.userid = userid;
        this.goalName = goalName;
        this.goalTotalAmt = goalTotalAmt;

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

}