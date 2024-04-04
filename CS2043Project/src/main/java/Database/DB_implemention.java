package Database;

import Exceptions.DateFormatException;

import java.sql.*;
import java.time.LocalDate;

public class DB_implemention implements DB_Methods{
    @Override
    public void addUser(String username, String password, String email, double monthlyIncome) {
        DB_User.addUser(username, password, email, monthlyIncome);
    }

    /**
     * Checks the amount of a budget used in given period, term type, & category.
     * @param userid user id to search.
     * @param category the category to search for.
     * @param StartDate the start date of search.
     * @param EndDate the end date of search.
     * @param type the type of transaction to search for.
     * @return the amount of money spent in the specified filter.
     */
    public double getBudgetUsage(int userid, String category, LocalDate StartDate, LocalDate EndDate, char type) throws DateFormatException {
        if (StartDate.compareTo(EndDate) >= 0)
            throw new DateFormatException("Start Date is Greater Then End Date");
        Connection dbConnection = DB_Access.Connect();
        CallableStatement dbStatement = null;
        double total = 0;

        try{
            dbStatement = dbConnection.prepareCall("{CALL getTransactionsByUserCategoryDateType(?,?,?,?,?)}");
            dbStatement.setInt(1, userid);
            dbStatement.setString(2, category);
            dbStatement.setDate(3, Date.valueOf(StartDate));
            dbStatement.setDate(4, Date.valueOf(EndDate));
            dbStatement.setString(5, String.valueOf(type));

            ResultSet resultSet = dbStatement.executeQuery();
            while (resultSet.next()) {
                double transactionAmount = resultSet.getDouble("amount");
                total += transactionAmount;
            }
        }
        catch(SQLException e){
            DB_Access.getSQLException(e);
        }
        finally{
            DB_Access.Closing(dbStatement, dbConnection);
        }
        return total;
    }
}
