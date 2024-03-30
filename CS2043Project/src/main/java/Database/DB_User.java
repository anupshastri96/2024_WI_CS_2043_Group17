package Database;

import Exceptions.UserNotFoundException;
import Objects.*;

import java.sql.*;
import java.util.LinkedList;

public class DB_User {
    public static void addUser(String username, String password, String email){
        Connection dbConnection = DB_Access.Connect();
        CallableStatement dbStatement = null;

        try{
            dbStatement = dbConnection.prepareCall("{CALL addUser(?,?,?,?)}");
            dbStatement.setString("usr", username);
            dbStatement.setString("password", password);
            dbStatement.setString("email", email);
            dbStatement.setDouble("monthly_income",0);
            dbStatement.executeQuery();
        }
        catch(SQLException e){
            DB_Access.getSQLException(e);
        }
        finally{
            DB_Access.Closing(dbStatement, dbConnection);
        }
    }

    /**
     * Gets the ID of a user given the username from .
     * @param username Username of userID to find.
     */
    public static int getUserIDbyName(String username) throws UserNotFoundException {
        Connection dbConnection = DB_Access.Connect();
        CallableStatement dbStatement = null;
        ResultSet dbResultSet = null;
        int userID = 0;

        try{
            dbStatement = dbConnection.prepareCall("{CALL getUserIDByName(?)}");
            dbStatement.setString("username", username);
            dbResultSet = dbStatement.executeQuery();

            if (dbResultSet.next()){
                userID = dbResultSet.getInt("user_id");
            }
            else throw new UserNotFoundException();
        }
        catch(SQLException e){
            DB_Access.getSQLException(e);
        } finally{
            DB_Access.Closing(dbStatement, dbConnection);
            DB_Access.ClosingResultSet(dbResultSet);
        }
        return userID;
    }

    public static User getUser(int user_id){
        Connection dbConnection = DB_Access.Connect();
        CallableStatement dbStatement = null;
        User temp = null;
        ResultSet dbResultSet = null;

        try{
            dbStatement = dbConnection.prepareCall("{CALL getUser(?)}");
            dbStatement.setInt("user_id", user_id);
            dbResultSet = dbStatement.executeQuery();
            while(dbResultSet.next()){
                String username = dbResultSet.getString("username");
                String password = dbResultSet.getString("password");
                String email = dbResultSet.getString("email");
                temp = new User(user_id, username, password, email);
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
    public static LinkedList<User> getUserList(){
        LinkedList<User> list = new LinkedList<>();
        Connection dbConnection = DB_Access.Connect();
        CallableStatement dbStatement = null;
        User user = null;
        ResultSet dbResultSet = null;
        try{
            dbStatement = dbConnection.prepareCall("{CALL getAllUsers()}");
            dbResultSet = dbStatement.executeQuery();
            while(dbResultSet.next()){
                int id = dbResultSet.getInt(1);
                String username = dbResultSet.getString(2);
                String password = dbResultSet.getString(3);
                String email = dbResultSet.getString(4);
                user = new User(id, username, password, email);
                list.add(user);
            }
        }
        catch (SQLException e){
            DB_Access.getSQLException(e);
        }
        return list;
    }
}
