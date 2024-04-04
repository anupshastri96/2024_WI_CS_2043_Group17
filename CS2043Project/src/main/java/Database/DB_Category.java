package Database;

import Objects.Category;
import Objects.Transaction;

import java.sql.*;
import java.time.LocalDate;
import java.util.LinkedList;

public class DB_Category {
    public static void addCategory(int userid, String category, double amount){
        Connection dbConnection = DB_Access.Connect();
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
            DB_Access.Closing(dbStatement, dbConnection);
        }
    }
    public static Category getCategory(int user_id, String categoryName){
        Connection dbConnection = DB_Access.Connect();
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
            DB_Access.Closing(dbStatement, dbConnection);
        }
        return category;
    }

    public static LinkedList<String> getCategoriesInRange(int userID, LocalDate startDate, LocalDate endDate){
        LinkedList<String> list = new LinkedList<>();
        Connection dbConnection = DB_Access.Connect();
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

    public static LinkedList<Category> getCategoryList(int userid){
        LinkedList<Category> list = new LinkedList<>();
        Connection dbConnection = DB_Access.Connect();
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
    public static boolean doesCategoryExist(int userId, String category){
        LinkedList<Category> categories = getCategoryList(userId);
        for (Category value : categories) {
            if (value.getName().equals(category)) {
                return true;
            }
        }
        return false;
    }
}
