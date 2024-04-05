package Database;

import Exceptions.UserNotFoundException;
import Objects.*;

import java.sql.*;
import java.util.LinkedList;

public class DB_User {

    /**
     * Login check to retrieve user data from database.
     * @param username username of user.
     * @param password password of user.
     * @return the user object, null if no user found.
     */
    public static User login(Connection dbConnection, String username, String password) {
        CallableStatement dbStatement = null;
        ResultSet dbResultSet = null;
        User user = null;

        try{
            dbStatement = dbConnection.prepareCall("{CALL login(?,?)}");
            dbStatement.setString("username", username);
            dbStatement.setString("password", password);
            dbResultSet = dbStatement.executeQuery();

            if (dbResultSet.next()) {
                int userId = dbResultSet.getInt("user_id");
                user = DB_User.getUser(dbConnection, userId);
            }
        }
        catch(SQLException e){
            DB_Access.getSQLException(e);
        }
        finally{
            DB_Access.Closing(dbStatement);
        }
        return user;
    }

    public static void addUser(Connection dbConnection, String username, String password, String email, double monthlyIncome){
        CallableStatement dbStatement = null;

        try{
            dbStatement = dbConnection.prepareCall("{CALL addUser(?,?,?,?)}");
            dbStatement.setString("usr", username);
            dbStatement.setString("password", password);
            dbStatement.setString("email", email);
            dbStatement.setDouble("monthly_income", monthlyIncome);
            dbStatement.executeQuery();
        }
        catch(SQLException e){
            DB_Access.getSQLException(e);
        }
        finally{
            DB_Access.Closing(dbStatement);
        }
    }

    /**
     * Gets the ID of a user given the username from .
     * @param username Username of userID to find.
     */
    public static Integer getUserIDbyName(Connection dbConnection, String username) throws UserNotFoundException {
        CallableStatement dbStatement = null;
        ResultSet dbResultSet = null;
        Integer userID = 0;

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
            DB_Access.Closing(dbStatement);
            DB_Access.ClosingResultSet(dbResultSet);
        }
        return userID;
    }

    public static User getUser(Connection dbConnection, int user_id){
        CallableStatement dbStatement = null;
        User temp = null;
        ResultSet dbResultSet = null;

        try{
            dbStatement = dbConnection.prepareCall("{CALL getUser(?)}");
            dbStatement.setInt("user_id", user_id);
            dbResultSet = dbStatement.executeQuery();
            while(dbResultSet.next()){
                String username = dbResultSet.getString("username");
                double monthlyIncome = dbResultSet.getDouble("monthly_income");
                String password = dbResultSet.getString("password");
                String email = dbResultSet.getString("email");
                temp = new User(user_id, username, password, email, monthlyIncome);
            }
        }
        catch(SQLException e){
            DB_Access.getSQLException(e);
        }
        finally{
            DB_Access.Closing(dbStatement);
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
                double monthlyIncome = dbResultSet.getDouble(5);
                user = new User(id, username, password, email, monthlyIncome);
                list.add(user);
            }
        }
        catch (SQLException e){
            DB_Access.getSQLException(e);
        }
        return list;
    }

    public static boolean checkIfUserExists(Connection dbConnection, String username){
        CallableStatement dbStatement = null;
        ResultSet dbResultSet = null;
        boolean result = false;

        try{
            dbStatement = dbConnection.prepareCall("{CALL checkIfUserExists(?)}");
            dbStatement.setString("username", username);
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