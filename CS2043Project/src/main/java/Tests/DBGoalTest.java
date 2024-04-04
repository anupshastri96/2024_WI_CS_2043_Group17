package Tests;

import Database.DB_Access;
import Database.DB_Goal;
import Database.DB_User;
import Objects.ListPrinters;
import Objects.User;

import java.sql.Connection;


public class DBGoalTest {
    public static void main(String[] args){

        //DB_Goal.addGoal(2, "Trip to germany", 1000, "2026-07-30"); // Successfully added a goal to the DB
        //DB_Goal.contributeToGoal(2,"Trip to germany", 10); // Successfully contributed to the goal, and updated in DB.
        //DB_Goal.removeGoal(2, "Trip to germany"); // Successfully deleted the goal from the DB

        //DB_Goal.addGoal(2, "Save for a Trip to Japan", 3000, "2025-05-01");
        //DB_Goal.addGoal(2, "Save for a Gaming Console", 500, "2024-11-15");
        //DB_Goal.addGoal(2, "Build an Emergency Fund", 2000, "2025-01-01");

        Connection dbConnection = DB_Access.Connect();
        ListPrinters.printGoalsTable(DB_Goal.getGoalList(dbConnection,2));
        DB_Access.Closing(dbConnection);
    }
}
