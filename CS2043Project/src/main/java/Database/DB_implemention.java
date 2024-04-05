package Database;

import Exceptions.DateFormatException;
import javafx.scene.chart.XYChart;
import javafx.util.Pair;

import java.sql.*;
import java.time.LocalDate;
import java.util.LinkedList;

public class DB_implemention{

    /**
     * Checks the amount of all budgets used in given period, & term type.
     * @param dbConnection connection to database.
     * @param userid user id to search.
     * @param StartDate the start date of search.
     * @param EndDate the end date of search.
     * @param type the type of transaction to search for.
     * @return list of all category spending totals
     */
    public static double budgetSum(Connection dbConnection, int userid, String category, LocalDate StartDate, LocalDate EndDate, char type) throws DateFormatException {

        if (StartDate.compareTo(EndDate) >= 0)
            throw new DateFormatException("Start Date is Greater Then End Date");
        CallableStatement dbStatement = null;
        ResultSet resultSet = null;
        double result = 0;

        try{
            dbStatement = dbConnection.prepareCall("{CALL budgetSum(?,?,?,?,?)}");
            dbStatement.setInt(1, userid);
            dbStatement.setString(2, category);
            dbStatement.setDate(3, Date.valueOf(StartDate));
            dbStatement.setDate(4, Date.valueOf(EndDate));
            dbStatement.setString(5, String.valueOf(type));
            resultSet = dbStatement.executeQuery();
            resultSet.next();
            result = resultSet.getDouble(1);
        }
        catch(SQLException e){
            DB_Access.getSQLException(e);
        }
        finally {
            DB_Access.Closing(dbStatement);
            DB_Access.ClosingResultSet(resultSet);
        }
        return result;
    }
}
