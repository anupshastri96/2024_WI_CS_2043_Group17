package Database;

import Objects.*;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DB_User {
    public static void addUser(String username, String password, String email){
        Connection dbConnection = DB_Access.Connect();
        CallableStatement dbStatement = null;

        try{
            dbStatement = dbConnection.prepareCall("{CALL addUser(?,?,?)}");
            dbStatement.setString("usr", username);
            dbStatement.setString("password", password);
            dbStatement.setString("email", email);
            dbStatement.executeQuery();
        }
        catch(SQLException e){
            DB_Access.getSQLException(e);
        }
        finally{
            DB_Access.Closing(dbStatement, dbConnection);
        }
    }

    public static User getUser(int user_id){
        Connection dbConnection = DB_Access.Connect();
        CallableStatement dbStatement = null;
        User temp = null;
        ResultSet dbResultSet = null;

        try{
            dbStatement = dbConnection.prepareCall("{CALL getUser(?)}");
            dbStatement.setInt(1, user_id);
            dbResultSet = dbStatement.executeQuery();
            while(dbResultSet.next()){
                String username = dbResultSet.getString("username");
                String password = dbResultSet.getString("password");
                String email = dbResultSet.getString("email");
                temp = new User(username, password, email);
            }
        }
        catch(SQLException e){
            DB_Access.getSQLException(e);
        }
        finally{
            DB_Access.Closing(dbStatement, dbConnection);
            DB_Access.ClosingResultSet(dbResultSet);
        }
        return temp;
    }
}
