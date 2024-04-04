package Database;

import Exceptions.DateFormatException;
import javafx.scene.chart.XYChart;
import javafx.util.Pair;

import java.sql.*;
import java.time.LocalDate;
import java.util.LinkedList;

public class DB_implemention{
    public void addUser(String username, String password, String email, double monthlyIncome) {
        DB_User.addUser(username, password, email, monthlyIncome);
    }

    /**
     * Checks the amount of all budgets used in given period, & term type.
     * @param userid user id to search.
     * @param StartDate the start date of search.
     * @param EndDate the end date of search.
     * @param type the type of transaction to search for.
     * @return list of all category spending totals
     */
    public static double budgetSum(int userid, String category, LocalDate StartDate, LocalDate EndDate, char type) throws DateFormatException {

        if (StartDate.compareTo(EndDate) >= 0)
            throw new DateFormatException("Start Date is Greater Then End Date");
        Connection dbConnection = DB_Access.Connect();
        CallableStatement dbStatement = null;
        double result = 0;

        try{
            dbStatement = dbConnection.prepareCall("{CALL budgetSum(?,?,?,?,?)}");
            dbStatement.setInt(1, userid);
            dbStatement.setString(2, category);
            dbStatement.setDate(3, Date.valueOf(StartDate));
            dbStatement.setDate(4, Date.valueOf(EndDate));
            dbStatement.setString(5, String.valueOf(type));
            ResultSet resultSet = dbStatement.executeQuery();
            resultSet.next();
            result = resultSet.getDouble(1);
        }
        catch(SQLException e){
            DB_Access.getSQLException(e);
        }
        finally{
            DB_Access.Closing(dbStatement, dbConnection);
        }
        return result;
    }
}
