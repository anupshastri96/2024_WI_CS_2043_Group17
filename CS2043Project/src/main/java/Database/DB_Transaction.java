package Database;
import java.sql.*;
import java.time.LocalDate;
import java.util.LinkedList;
import Objects.*;

public class DB_Transaction {
    public static void addTransaction(int userid, Date date, String name, char type, double amount, String description, String category){
        Connection dbConnection = DB_Access.Connect();
        CallableStatement dbStatement = null;

        try{
            dbStatement = dbConnection.prepareCall("{CALL addTransaction(?,?,?,?,?,?,?)}");
            dbStatement.setInt("userid", userid);
            dbStatement.setDate("date", date);
            dbStatement.setString("name", name);
            dbStatement.setString("type", "" + type);
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

}
