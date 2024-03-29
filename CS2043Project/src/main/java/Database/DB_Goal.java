package Database;

import Objects.Category;
import Objects.Goal;

import java.sql.*;
import java.util.LinkedList;

public class DB_Goal {
    public static void addGoal(int userid, String name, double amount, Date date){
        Connection dbConnection = DB_Access.Connect();
        CallableStatement dbStatement = null;
        try{

            dbStatement = dbConnection.prepareCall("{CALL addGoal(?,?,?,?)}");
            dbStatement.setInt(1, userid);
            dbStatement.setString(2, name);
            dbStatement.setDouble(3, amount);
            dbStatement.setDate(4, date);
            dbStatement.executeQuery();

        }
        catch(SQLException e){
            DB_Access.getSQLException(e);
        }
        finally{
            DB_Access.Closing(dbStatement, dbConnection);
        }
    }

    public static void removeGoal(int userid, String name){
        Connection dbConnection = DB_Access.Connect();
        CallableStatement dbStatement = null;
        try{

            dbStatement = dbConnection.prepareCall("{CALL deleteGoal(?,?)}");
            dbStatement.setInt(1, userid);
            dbStatement.setString(2, name);
            dbStatement.executeQuery();
        }
        catch(SQLException e){
            DB_Access.getSQLException(e);
        }
        finally{
            DB_Access.Closing(dbStatement, dbConnection);
        }
    }
    public static LinkedList<Goal> getGoalList(int userid){
        LinkedList<Goal> list = new LinkedList<>();
        Connection dbConnection = DB_Access.Connect();
        CallableStatement dbStatement = null;
        ResultSet dbResultSet = null;
        Goal goal = null;
        try{
            dbStatement = dbConnection.prepareCall("{CALL getAllGoals(?)}");
            dbStatement.setInt(1, userid);
            dbResultSet = dbStatement.executeQuery();
            while(dbResultSet.next()){
                String name =  dbResultSet.getString(2);
                double amountSaved = dbResultSet.getDouble(3);
                double amountTotal = dbResultSet.getDouble(4);
                Date date = dbResultSet.getDate(5);
                goal = new Goal(userid, name, amountSaved,amountTotal,date);
                list.add(goal);
            }
        }
        catch (SQLException e){
            DB_Access.getSQLException(e);
        }
        return list;
    }

}
