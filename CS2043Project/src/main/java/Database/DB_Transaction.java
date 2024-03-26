package Database;
import java.sql.*;
import java.time.LocalDate;
import java.util.LinkedList;
import Objects.*;

public class DB_Transaction {
    public static void addTransaction(int userid, String name, char type, double amount, String description, String category){
        Connection dbConnection = DB_Access.Connect();
        CallableStatement dbStatement = null;
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        try{

            dbStatement = dbConnection.prepareCall("{CALL addTransaction(?,?,?,?,?,?,?)}");
            dbStatement.setInt("userid", userid);
            dbStatement.setDate("date", sqlDate);
            dbStatement.setString("name", name);
            dbStatement.setString("type", String.valueOf(type));
            dbStatement.setDouble("amount", amount);
            dbStatement.setString("description", description);
            dbStatement.setString("category", category);
            dbStatement.executeQuery();
        }
        catch(SQLException e){
            DB_Access.getSQLException(e);
        }
        finally{
            DB_Access.Closing(dbStatement, dbConnection);
        }
    }
    LinkedList<Transaction> refreshList(User user){
        LinkedList<Transaction> list = new LinkedList<>();
        Connection dbConnection = DB_Access.Connect();
        CallableStatement dbStatement = null;
        Transaction transaction = null;
        ResultSet dbResultSet = null;
        try{
            while(dbResultSet.next()){
                int userId = 0;
                int transactionId = 0;
                Date date = null; // The date of the transaction
                String name = null; // The name associated with the transaction
                char type = 0; // The type of transaction, e.g., 'I' for income, 'E' for expense
                double amount = 0; // The amount of the transaction
                String description = null; // A description of the transaction
                Category category = null;

                if(transactionId == user.getUserId()){
                    transaction = new Transaction(userId, transactionId, date, name, type, amount, description, category);
                    list.add(transaction);
                }
            }
        }
        catch (SQLException e){
            DB_Access.getSQLException(e);
        }
        return list;
    }

    public static void updateTransaction(int transactionId, String name, double amount, String description, String category) {
        Connection dbConnection = DB_Access.Connect();
        CallableStatement dbStatement = null;

        try {
            dbStatement = dbConnection.prepareCall("{CALL updateTransaction(?,?,?,?,?,?)}");
            dbStatement.setInt(1, transactionId);
            dbStatement.setString(2, name);
            dbStatement.setDouble(3, amount);
            dbStatement.setString(4, description);
            dbStatement.setString(5, category);
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
        ResultSet dbResultSet = null;
        Transaction transaction = null;

        try {
            dbStatement = dbConnection.prepareCall("{CALL getTransactionById(?)}");
            dbStatement.setInt(1, transactionId);
            dbResultSet = dbStatement.executeQuery();

            if (dbResultSet.next()) {
                int userId = dbResultSet.getInt("user_id");
                Date date = dbResultSet.getDate("date");
                String name = dbResultSet.getString("name");
                char type = dbResultSet.getString("type").charAt(0);
                double amount = dbResultSet.getDouble("amount");
                String description = dbResultSet.getString("description");
                Category category = new Category(dbResultSet.getString("category"), dbResultSet.getDouble("budget"));

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
