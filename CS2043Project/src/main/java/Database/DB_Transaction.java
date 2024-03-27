package Database;
import java.sql.*;
import java.util.LinkedList;
import Objects.*;

public class DB_Transaction {
    public static void addTransaction(int userid, String name, char type, double amount, String description, String category){
        Connection dbConnection = DB_Access.Connect();
        CallableStatement dbStatement = null;
        try{

            dbStatement = dbConnection.prepareCall("{CALL addTransaction(?,?,?,?,?,?,?)}");
            dbStatement.setInt(1, userid);
            dbStatement.setString(2, name);
            dbStatement.setString(3, String.valueOf(type));
            dbStatement.setDouble(4, amount);
            dbStatement.setString(5, description);
            dbStatement.setString(6, category);
            dbStatement.executeQuery();

        }
        catch(SQLException e){
            DB_Access.getSQLException(e);
        }
        finally{
            DB_Access.Closing(dbStatement, dbConnection);
        }
    }
    public static LinkedList<Transaction> getTransactionList(int userId){
        LinkedList<Transaction> list = new LinkedList<>();
        Connection dbConnection = DB_Access.Connect();
        CallableStatement dbStatement = null;
        Transaction transaction = null;
        ResultSet dbResultSet = null;
        try{
            dbStatement = dbConnection.prepareCall("{CALL getTransactionByUser(?)}");
            dbStatement.setInt(1, userId);
            dbResultSet = dbStatement.executeQuery();
            while(dbResultSet.next()){
                int transactionId = dbResultSet.getInt(2);
                Date date = dbResultSet.getDate(3);
                String name = dbResultSet.getString(4);
                char type = dbResultSet.getString(5).charAt(0);
                double amount = dbResultSet.getDouble(6);
                String description = dbResultSet.getString(7);
                String categoryName = dbResultSet.getString(8);

                Category category = DB_Category.getCategory(userId, categoryName);
                transaction = new Transaction(userId, transactionId, date, name, type, amount, description, category);
                list.add(transaction);
            }
        }
        catch (SQLException e){
            DB_Access.getSQLException(e);
        }
        return list;
    }

    public static void updateTransaction(int transactionId, Date date, String name, char type, double amount, String description, String category) {
        Connection dbConnection = DB_Access.Connect();
        CallableStatement dbStatement = null;
        //TODO Test this method

        try {
            dbStatement = dbConnection.prepareCall("{CALL updateTransaction(?,?,?,?,?,?)}");
            dbStatement.setInt(2, transactionId);
            dbStatement.setDate(3, date);
            dbStatement.setString(4, name);
            dbStatement.setString(5, type + "");
            dbStatement.setDouble(5, amount);
            dbStatement.setString(6, description);
            dbStatement.setString(7, category);
            dbStatement.executeUpdate();
        } catch (SQLException e) {
            DB_Access.getSQLException(e);
        } finally {
            DB_Access.Closing(dbStatement, dbConnection);
        }
    }

    public static void deleteTransaction(int transactionId) {
        Connection dbConnection = DB_Access.Connect();
        CallableStatement dbStatement = null;

        try {
            dbStatement = dbConnection.prepareCall("{CALL deleteTransaction(?)}");
            dbStatement.setInt(1, transactionId);
            dbStatement.executeUpdate();
        } catch (SQLException e) {
            DB_Access.getSQLException(e);
        } finally {
            DB_Access.Closing(dbStatement, dbConnection);
        }
    }

    public static Transaction getTransactionById(int transactionId) {
        Connection dbConnection = DB_Access.Connect();
        CallableStatement dbStatement = null;
        ResultSet dbResultSet;
        Transaction transaction = null;

        try {
            dbStatement = dbConnection.prepareCall("{CALL getTransactionById(?)}");
            dbStatement.setInt(1, transactionId);
            dbResultSet = dbStatement.executeQuery();

            if (dbResultSet.next()) {
                int userId = dbResultSet.getInt(1);
                Date date = dbResultSet.getDate(3);
                String name = dbResultSet.getString(4);
                char type = dbResultSet.getString(5).charAt(0);
                double amount = dbResultSet.getDouble(6);
                String description = dbResultSet.getString(7);
                String categoryName = dbResultSet.getString(8);
                Category category = DB_Category.getCategory(userId, categoryName);

                transaction = new Transaction(userId, transactionId, date, name, type, amount, description, category);
            }
        } catch (SQLException e) {
            DB_Access.getSQLException(e);
        } finally {
            DB_Access.Closing(dbStatement, dbConnection);
        }

        return transaction;
    }
}
