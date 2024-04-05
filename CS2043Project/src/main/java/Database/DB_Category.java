package Database;

import Objects.Category;
import Objects.Transaction;

import java.sql.*;
import java.time.LocalDate;
import java.util.LinkedList;

public class DB_Category {
    public static void addCategory(Connection dbConnection, int userid, String category, double amount){
        CallableStatement dbStatement = null;

        try{
            dbStatement = dbConnection.prepareCall("{CALL addCategory(?,?,?)}");
            dbStatement.setInt(1, userid);
            dbStatement.setString(2, category);
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
    public static Category getCategory(Connection dbConnection, int user_id, String categoryName){
        CallableStatement dbStatement = null;
        Category category = null;
        ResultSet dbResultSet = null;
        try {
            dbStatement = dbConnection.prepareCall("{CALL getCategory(?,?)}");
            dbStatement.setInt(1, user_id);
            dbStatement.setString(2, categoryName);
            dbResultSet = dbStatement.executeQuery();
            while(dbResultSet.next()) {
                double budget = dbResultSet.getDouble(3);
                category = new Category(user_id, categoryName, budget);
            }
        }
        catch(SQLException e){
            DB_Access.getSQLException(e);
        }
        finally{
            DB_Access.Closing(dbStatement);
        }
        return category;
    }

    public static void updateCategory(Connection dbConnection, int userId, String categoryName, String newCategoryName, Integer newBudget){
        CallableStatement dbStatement = null;
        try {
            dbStatement = dbConnection.prepareCall("{CALL updateCategory(?,?,?,?)}");
            dbStatement.setInt(1, userId);
            dbStatement.setString(2, categoryName);
            dbStatement.setString(3, newCategoryName);
            dbStatement.setInt(4, newBudget);
            dbStatement.executeQuery();
        }
        catch(SQLException e){
            DB_Access.getSQLException(e);
        }
        finally{
            DB_Access.Closing(dbStatement);
        }
    }

    public static void deleteCategory(Connection dbConnection, int userId, String categoryName){
        CallableStatement dbStatement = null;
        try {
            dbStatement = dbConnection.prepareCall("{CALL deleteCategory(?,?)}");
            dbStatement.setInt(1, userId);
            dbStatement.setString(2, categoryName);
            dbStatement.executeQuery();
        }
        catch(SQLException e){
            DB_Access.getSQLException(e);
        }
        finally{
            DB_Access.Closing(dbStatement);
        }
    }

    public static LinkedList<String> getCategoriesInRange(Connection dbConnection, int userID, LocalDate startDate, LocalDate endDate){
        LinkedList<String> list = new LinkedList<>();
        CallableStatement dbStatement = null;
        ResultSet dbResultSet = null;

        try{
            dbStatement = dbConnection.prepareCall("{CALL getCategoriesInRange(?,?,?)}");
            dbStatement.setInt(1, userID);
            dbStatement.setDate(2, Date.valueOf(startDate));
            dbStatement.setDate(3, Date.valueOf(endDate));
            dbResultSet = dbStatement.executeQuery();
            while(dbResultSet.next()){
                list.add(dbResultSet.getString(1));
            }
        }
        catch (SQLException e){
            DB_Access.getSQLException(e);
        }
        return list;
    }

    public static LinkedList<Category> getCategoryList(Connection dbConnection, int userid){
        LinkedList<Category> list = new LinkedList<>();
        CallableStatement dbStatement = null;
        ResultSet dbResultSet = null;
        Category category = null;
        try{
            dbStatement = dbConnection.prepareCall("{CALL getCategoryByUserid(?)}");
            dbStatement.setInt(1, userid);
            dbResultSet = dbStatement.executeQuery();
            while(dbResultSet.next()){
                String name =  dbResultSet.getString(2);
                double budget = dbResultSet.getDouble(3);
                category = new Category(userid, name, budget);
                list.add(category);
            }
        }
        catch (SQLException e){
            DB_Access.getSQLException(e);
        }
        return list;
    }

    public static boolean checkIfCategoryExists(Connection dbConnection, int userId, String category){
        CallableStatement dbStatement = null;
        ResultSet dbResultSet = null;
        boolean result = false;

        try{
            dbStatement = dbConnection.prepareCall("{CALL checkIfCategoryExists(?,?)}");
            dbStatement.setInt(1, userId);
            dbStatement.setString(2, category);
            dbResultSet = dbStatement.executeQuery();

            if (dbResultSet.next()){
                if (dbResultSet.getInt(1) == 1){
                    result = true;
                }
            }
        }
        catch(SQLException e){
            DB_Access.getSQLException(e);
        } finally{
            DB_Access.Closing(dbStatement);
            DB_Access.ClosingResultSet(dbResultSet);
        }
        return result;
    }
}