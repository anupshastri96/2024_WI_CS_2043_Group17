package Database;

import Objects.Category;
import Objects.DateConversion;
import Objects.Goal;

import java.sql.*;
import java.util.LinkedList;

public class DB_Goal {
    public static void addGoal(Connection dbConnection, int userid, String name, double totalAmount, String date){

        CallableStatement dbStatement = null;
        try{

            dbStatement = dbConnection.prepareCall("{CALL addGoal(?,?,?,?)}");
            dbStatement.setInt(1, userid);
            dbStatement.setString(2, name);
            dbStatement.setDouble(3, totalAmount);
            dbStatement.setString(4, date);
            dbStatement.executeUpdate();

        }
        catch(SQLException e){
            DB_Access.getSQLException(e);
        }
        finally{
            DB_Access.Closing(dbStatement);
        }
    }

    public static void removeGoal(Connection dbConnection, int userid, String name){
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
            DB_Access.Closing(dbStatement);
        }
    }
    public static LinkedList<Goal> getGoalList(Connection dbConnection, int userid){
        LinkedList<Goal> list = new LinkedList<>();
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
        finally{
            DB_Access.Closing(dbStatement);
            DB_Access.ClosingResultSet(dbResultSet);
        }
        return list;
    }

    public static void contributeToGoal(Connection dbConnection, int userId, String name, double amount){
        CallableStatement dbStatement = null;
        try{
            dbStatement = dbConnection.prepareCall("{CALL contributeToGoal(?,?,?)}");
            dbStatement.setInt(1, userId);
            dbStatement.setString(2, name);
            dbStatement.setDouble(3, amount);
            dbStatement.executeQuery();
        }
        catch(SQLException e){
            DB_Access.getSQLException(e);
        }
        finally{
            DB_Access.Closing(dbStatement);
        }
    }

}